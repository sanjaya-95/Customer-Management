package com.example.shoppingnet.util.mappers;

import com.example.shoppingnet.dto.paginated.PageItemDTO;
import com.example.shoppingnet.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface ItemMapper {

    List<PageItemDTO> ListDTOTopage(Page<Item> items);
}
