package in.gv.billingsoftware1.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

	
	boolean deleteFile(String imgUrl);
	
	String uploadFile(MultipartFile file);
}
