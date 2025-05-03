package in.gv.billingsoftware1.service;


import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import in.gv.billingsoftware1.io.CategoryRequest;
import in.gv.billingsoftware1.io.CategoryResponse;

public interface CategoryService {

	CategoryResponse add(CategoryRequest request,MultipartFile file) throws IOException ;
	List<CategoryResponse> read();
	void delete(String categoryId);
}
