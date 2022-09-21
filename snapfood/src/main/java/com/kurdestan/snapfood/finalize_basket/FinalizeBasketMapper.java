package com.kurdestan.snapfood.finalize_basket;

import com.kurdestan.snapfood.user.UserMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring", uses = {UserMapper.class})
public interface FinalizeBasketMapper {

    FinalizeBasket toFinalizeBasket(FinalizeBasketDTO finalizeBasketDTO);
    FinalizeBasketDTO toFinalizeBasketDTO(FinalizeBasket finalizeBasket);
    List<FinalizeBasket> toFinalizeBasketList(List<FinalizeBasketDTO> finalizeBasketDTOS);
    List<FinalizeBasketDTO> toFinalizeBasketDTOs(List<FinalizeBasket> finalizeBasketList);
}
