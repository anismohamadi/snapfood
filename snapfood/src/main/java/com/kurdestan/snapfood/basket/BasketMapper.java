package com.kurdestan.snapfood.basket;


import com.kurdestan.snapfood.supplier.SupplierMapper;
import com.kurdestan.snapfood.user_address.UserAddressMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring", uses = {UserAddressMapper.class, SupplierMapper.class})
public interface BasketMapper {

    Basket toBasket(BasketDTO basketDTO);
    BasketDTO toBasketDTO(Basket basket);
    List<Basket> toBasketList(List<BasketDTO> basketDTOS);
    List<BasketDTO> toBasketDTOs(List<Basket> basketList);

}
