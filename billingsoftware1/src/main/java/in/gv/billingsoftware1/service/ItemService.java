package in.gv.billingsoftware1.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import in.gv.billingsoftware1.io.ItemRequest;
import in.gv.billingsoftware1.io.ItemResponse;

public interface ItemService {
   ItemResponse add(ItemRequest request, MultipartFile file) throws IOException;
    List<ItemResponse> fetchItems();
    void deleteItem(String itemId);
} 
