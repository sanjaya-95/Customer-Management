package com.example.shoppingnet.controller;

import com.example.shoppingnet.dto.CustomerDTO;
import com.example.shoppingnet.dto.ItemDTO;
import com.example.shoppingnet.dto.paginated.PageItemDTO;
import com.example.shoppingnet.entity.Item;
import com.example.shoppingnet.service.CustomerService;
import com.example.shoppingnet.service.ItemService;
import com.example.shoppingnet.util.StandardResponse;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;



    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemDTO itemDTO) {
        try {
            ItemDTO savedItem = itemService.saveItem(itemDTO);
//        return new ResponseEntity<String>(savedItem, HttpStatus.CREATED);
            return new ResponseEntity<StandardResponse>(new StandardResponse(201, "ok", savedItem ),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(new StandardResponse(401, "false", e.getMessage() ),HttpStatus.ALREADY_REPORTED);
        }
    }

    @GetMapping(
            path = "/getById",
            params = "id"
    )

    public ItemDTO getItemById(@RequestParam(value = "id") int itemId){
       ItemDTO itemDTO = itemService.getItemById(itemId);
       return itemDTO;
    }

    @GetMapping(path= "/getAll")

    public List<ItemDTO> getAllItems() {
        List<ItemDTO> allItems = itemService.getAllItems();
        return allItems;
    }

        @GetMapping(
                path = "/getAllItems-By-active",
                params = "status"
        )

        public ResponseEntity<StandardResponse> getAllItemsByActive(@RequestParam(value = "status") boolean activeStatus){
            List<ItemDTO> allActiveItems = itemService.getAllItemsByActive(activeStatus);
            return new ResponseEntity<StandardResponse> (
                    new StandardResponse(200, "Success", allActiveItems ),
                    HttpStatus.OK
            );
        }

        @GetMapping(
                path = "/get-by-name",
                params = "name"
        )
        public ResponseEntity<StandardResponse> getItemNameAndStatus(@RequestParam(value = "name") String itemName){
            List<ItemDTO> ItemsList = itemService.getItemNameAndStatus(itemName);
            return new ResponseEntity<StandardResponse> (
                    new StandardResponse(200, "Success", ItemsList ),
                    HttpStatus.OK
            );
        }

/*    @GetMapping(
            path  ="/viewItems",
            params = {"page", "size"}
    )

    public ResponseEntity<StandardResponse> getItemsWithPagin(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        List<PageItemDTO> pageItemDTO = itemService.getItemsWithPagin(page,size );
        return new ResponseEntity<StandardResponse> (
                new StandardResponse(200, "Success", pageItemDTO ),
                HttpStatus.OK
        );
    }*/

 /*   @GetMapping(
            path  ="/viewItems",
            params = {"page", "size"} )

    public ResponseEntity<StandardResponse> getItemsWithPagin(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) throws NotFoundException {
        PageItemDTO pageItemDTO = itemService.findAllByPages(page, size);
        return new ResponseEntity<StandardResponse> (
                new StandardResponse(200, "Success", pageItemDTO ),
                HttpStatus.OK );
    }*/




    @DeleteMapping(
            path = "delete",
            params = "id"
    )

    public String deleteItem(@RequestParam(value = "id") int itemId){
        String deleted = itemService.deleteItem(itemId);
        return deleted;
    }
}
