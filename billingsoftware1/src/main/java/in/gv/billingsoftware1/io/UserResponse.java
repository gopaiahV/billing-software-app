package in.gv.billingsoftware1.io;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	private String userId;
	private String name;
	private String email;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String role;
}

