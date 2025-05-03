package in.gv.billingsoftware1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AWSConfig {

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.AP_SOUTH_1) // Replace with your AWS region
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create("your-access-key", "your-secret-key")
                        )
                )
                //.endpointOverride(URI.create("https://s3.ap-south-1.amazonaws.com")) // Optional
                .build();
    }
}


/*
 * package in.gv.billingsoftware1.config;
 * 
 * import org.springframework.beans.factory.annotation.Value; import
 * org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration;
 * 
 * import software.amazon.awssdk.auth.credentials.AwsBasicCredentials; import
 * software.amazon.awssdk.auth.credentials.StaticCredentialsProvider; import
 * software.amazon.awssdk.regions.Region; import
 * software.amazon.awssdk.services.s3.S3Client;
 * 
 * 
 * 
 * @Configuration
 * 
 * public class AWSConfig {
 * 
 * @Value("${aws.access.key}")//dummy private String accessKey;
 * 
 * @Value("${aws.secret.key}")//dummy private String secretKey;
 * // @Value("{aws.region}")
 * 
 * @Value("${aws.region}") private String region;
 * 
 * @Value("${aws.s3.enabled:false}") // <-- default false private boolean
 * s3Enabled;
 * 
 * @Bean
 * 
 * @ConditionalOnProperty(name = "aws.s3.enabled", havingValue = "true",
 * matchIfMissing = false) public S3Client s3Client() { return
 * S3Client.builder() .region(Region.of(region))
 * .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.
 * create(accessKey, secretKey))) .build(); }
 * 
 * }
 */