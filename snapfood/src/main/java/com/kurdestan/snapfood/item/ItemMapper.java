package com.kurdestan.snapfood.item;

import com.kurdestan.snapfood.supplier_category.SupplierCategoryMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring", uses = {SupplierCategoryMapper.class})
public interface ItemMapper {

    Item toItem(ItemDTO itemDTO);
    ItemDTO toItemDTO(Item item);
    List<Item> toItemList(List<ItemDTO> itemDTOS);
    List<ItemDTO> toItemDTOs(List<Item> itemList);

}
