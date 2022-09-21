package com.kurdestan.snapfood.basket_item;


import com.kurdestan.snapfood.basket.BasketDTO;
import com.kurdestan.snapfood.common.BaseDTO;
import com.kurdestan.snapfood.item.ItemDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@Data
public class BasketItemDTO extends BaseDTO {

    @ApiModelProperty(required = true,hidden = false)
    private String name;

    @ApiModelProperty(required = true,hidden = false)
    private  Integer count;

    @ApiModelProperty(required = true,hidden = false)
    private BasketDTO basket;

    @ApiModelProperty(required = true,hidden = false)
    private ItemDTO item;


}
