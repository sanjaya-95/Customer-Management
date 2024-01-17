package com.example.shoppingnet.service;

import com.example.shoppingnet.dto.ItemDTO;
import com.example.shoppingnet.dto.paginated.PageItemDTO;
import javassist.NotFoundException;

import java.util.List;

public interface ItemService {
   public ItemDTO saveItem(ItemDTO itemDTO);

    ItemDTO getItemById(int itemId);


    List<ItemDTO> getAllItems();

    String deleteItem(int itemId);

    List<ItemDTO> getAllItemsByActive(boolean activeStatus);

    List<ItemDTO> getItemNameAndStatus(String itemName);

// PageItemDTO findAllByPages(int page, int size);

// PageItemDTO getItemwithPaginated(int page, int size) throws NotFoundException;


//    PageItemDTO getItemsWithPagin(int page, int size) ;
}
