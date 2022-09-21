package com.kurdestan.snapfood.finalize_basket;

import com.kurdestan.snapfood.common.BaseDTO;
import com.kurdestan.snapfood.user.UserDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class FinalizeBasketDTO extends BaseDTO {

   @ApiModelProperty(required = true,hidden = false)
    private Status status;

    @ApiModelProperty(required = true,hidden = false)
    private  Double paidPrice;

    @ApiModelProperty(required = true,hidden = false)
    private UserDTO user;
}
