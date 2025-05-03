package in.gv.billingsoftware1.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data

@NoArgsConstructor //allows Jackson to instantiate the object during deserialization.
@AllArgsConstructor //keeps your builder and manual instantiations working.
 public class CategoryRequest {
	private String name;
	private String description;
	private String bgColor;

}
