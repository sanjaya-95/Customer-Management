package com.example.shoppingnet.dto.paginated;

import com.example.shoppingnet.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageItemDTO {

    List<ItemDTO> list;
    private int dataCount;
}
