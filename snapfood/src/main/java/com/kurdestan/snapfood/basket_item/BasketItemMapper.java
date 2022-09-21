package com.kurdestan.snapfood.basket_item;


import com.kurdestan.snapfood.basket.BasketMapper;
import com.kurdestan.snapfood.item.ItemMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring", uses = {BasketMapper.class, ItemMapper.class})
public interface BasketItemMapper {

    BasketItem toBasketItem(BasketItemDTO basketItemDTO);
    BasketItemDTO toBasketItemDTO(BasketItem basketItem);
    List<BasketItem> toBaskeItemtList(List<BasketItemDTO> basketItemDTOS);
    List<BasketItemDTO> toBasketItemDTOs(List<BasketItem> basketItemList);

}
