
package in.gv.billingsoftware1.service.impl;

import in.gv.billingsoftware1.service.FileUploadService;
//import lombok.Builder;
//import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.UUID;
//@Builder
//@Data
@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Value("${aws.access.key:}")
    private String accessKey;

    @Value("${aws.secret.key:}")
    private String secretKey;

    private final S3Client s3Client;

    private boolean isAwsConfigured() {
        return accessKey != null && !accessKey.isBlank() && secretKey != null && !secretKey.isBlank();
    }

    @Override
    public String uploadFile(MultipartFile file) {
        if (!isAwsConfigured()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "AWS credentials are not configured properly");
        }

        String filenameExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String key = UUID.randomUUID().toString() + "." + filenameExtension;

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .acl("public-read")
                    .contentType(file.getContentType())
                    .build();

            PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            if (response.sdkHttpResponse().isSuccessful()) {
                return "https://" + bucketName + ".s3.amazonaws.com/" + key;
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while uploading the image");
            }

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while uploading the file");
        }
    }

    @Override
    public boolean deleteFile(String imgUrl) {
        if (!isAwsConfigured()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "AWS credentials are not configured properly");
        }

        String filename = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
        return true;
    }
}

/* 
 * package in.gv.billingsoftware1.service.impl;
 * 
 * import in.gv.billingsoftware1.service.FileUploadService; import
 * lombok.RequiredArgsConstructor; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.stereotype.Service; import
 * org.springframework.web.multipart.MultipartFile; import
 * org.springframework.web.server.ResponseStatusException; import
 * software.amazon.awssdk.core.sync.RequestBody; import
 * software.amazon.awssdk.services.s3.S3Client; import
 * software.amazon.awssdk.services.s3.model.DeleteObjectRequest; import
 * software.amazon.awssdk.services.s3.model.PutObjectRequest; import
 * software.amazon.awssdk.services.s3.model.PutObjectResponse;
 * 
 * import java.io.IOException; import java.util.UUID;
 * 
 * @Service
 * 
 * @RequiredArgsConstructor public class FileUploadServiceImpl implements
 * FileUploadService {
 * 
 * @Value("${aws.bucket.name}") private String bucketName; private final
 * S3Client s3Client;
 * 
 * @Override public String uploadFile(MultipartFile file) { String
 * filenameExtension =
 * file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(
 * ".")+1); String key = UUID.randomUUID().toString()+"."+filenameExtension; try
 * { PutObjectRequest putObjectRequest = PutObjectRequest.builder()
 * .bucket(bucketName) .key(key) .acl("public-read")
 * .contentType(file.getContentType()) .build(); PutObjectResponse response =
 * s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
 * 
 * if (response.sdkHttpResponse().isSuccessful()) { return
 * "https://"+bucketName+".s3.amazonaws.com/"+key; } else { throw new
 * ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
 * "An error occured while uploading the image"); }
 * 
 * }catch (IOException e) { throw new
 * ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
 * "An error occured while uploading the file"); } }
 * 
 * @Override public boolean deleteFile(String imgUrl) { String filename =
 * imgUrl.substring(imgUrl.lastIndexOf("/")+1); DeleteObjectRequest
 * deleteObjectRequest = DeleteObjectRequest.builder() .bucket(bucketName)
 * .key(filename) .build(); s3Client.deleteObject(deleteObjectRequest); return
 * true; } }
 */