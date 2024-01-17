package com.example.shoppingnet.service.impl;


import com.example.shoppingnet.dto.ItemDTO;
import com.example.shoppingnet.dto.paginated.PageItemDTO;
import com.example.shoppingnet.entity.Item;
import com.example.shoppingnet.repo.ItemRepo;
import com.example.shoppingnet.service.ItemService;
import com.example.shoppingnet.util.mappers.ItemMapper;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Service
//@RequiredArgsConstructor


public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;
//    private final ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;
//    private final ModelMapper modelMapper;

   /* @Autowired
    private ItemMapper itemMapper;*/



    @Override
    public ItemDTO saveItem(ItemDTO itemDTO){

        List<Item> it = itemRepo.findBySupplierPrice(Double.parseDouble("1300"));


        List<Item> it2 = itemRepo.searchByxyz("ball");
        List<Tuple> it3 = itemRepo.searchByxyz2("cake");

        it3.forEach(item -> {System.out.println(item.get("customer_address")); System.out.println(item.get("item_id"));});

        Item item = modelMapper.map(itemDTO, Item.class);

        if (!itemRepo.existsById(item.getItemId())) {
            Item i = itemRepo.save(item);
            //-------------------------------------------
            ItemDTO i2 = modelMapper.map(i, ItemDTO.class);
//            return item.getItemName() + " saved successful";
            return  i2;

        } else {
            throw new DuplicateKeyException("Already added this id");
        }
    }

    @Override
    public ItemDTO getItemById(int itemId) {
        if(itemRepo.existsById(itemId)){
            Item item = itemRepo.getById(itemId);

            ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
            return itemDTO;
        }else{
            throw new RuntimeException("No Item found in this id");
        }

    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> getAllItems = itemRepo.findAll();
//        ItemDTO items = modelMapper.map(getAllItems, new TypeToken<ArrayList<ItemDTO>>(){}.getType());
        return modelMapper.map(getAllItems, new TypeToken<ArrayList<ItemDTO>>(){}.getType());
    }




    @Override
    public String deleteItem(int itemId) {
    if(itemRepo.existsById(itemId)){
        itemRepo.deleteById(itemId);
        return "Deleted Successfully " +itemId;
    }else {
        throw new RuntimeException("No item found");
    }

    }

    @Override
    public List<ItemDTO> getAllItemsByActive(boolean activeStatus) {
        List<Item> itemList2 = itemRepo.findAllByActiveStatusEquals(activeStatus);
        List<ItemDTO> list2 = modelMapper.map(itemList2, new TypeToken<ArrayList<ItemDTO>>(){}.getType());
        return list2;
    }

    @Override
    public List<ItemDTO> getItemNameAndStatus(String itemName) {
        boolean b = true;
        List<Item> itemsList = itemRepo.findAllByItemNameEqualsAndActiveStatusEquals(itemName,b);
        if(itemsList.size()>0){
            List<ItemDTO> itemlist1 = modelMapper.map(itemsList, new TypeToken<ArrayList<ItemDTO>>(){}.getType());
            return itemlist1;
        }else {
            throw new RuntimeException("No item found");
        }

    }

/*    @Override
    public PageItemDTO getItemwithPaginated(int page, int size) throws NotFoundException {
        Page<Item> items = itemRepo.findAllByPages(PageRequest.of(page, size));
        if(items.getSize() <1){
            throw new NotFoundException("No item found");
        } else{
            PageItemDTO pageItemDTO = new PageItemDTO(
                    itemMapper.ListDTOTopage(items),
                    2

            );

            return pageItemDTO;
        }

    }*/


/*
    public PageItemDTO getItemsWithPagin(int page, int size)  {
        Page<Item> items = itemRepo.findAllByPages(PageRequest.of(page, size));

            PageItemDTO pageItemDTO=  modelMapper.map(Page<Item>,PageItemDTO.class);

        PageItemDTO pageItemDTO=  modelMapper.map(Page<Item>,PageItemDTO.class);
            return pageItemDTO;
        }

    }
*/


}
