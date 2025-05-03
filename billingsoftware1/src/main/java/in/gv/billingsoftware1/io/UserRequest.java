package in.gv.billingsoftware1.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	private String name;
	private String email;
	private String password;
	private String role;
}
