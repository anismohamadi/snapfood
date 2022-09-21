package com.kurdestan.snapfood.basket;

import com.kurdestan.snapfood.common.BaseDTO;
import com.kurdestan.snapfood.supplier.SupplierDTO;
import com.kurdestan.snapfood.user_address.UserAddressDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasketDTO extends BaseDTO {


    @ApiModelProperty(required = true,hidden = false)
    private Double  paiedPrice;

    @ApiModelProperty(required = true,hidden = false)
    private SupplierDTO supplier;

    @ApiModelProperty(required = true,hidden = false)
    private UserAddressDTO userAddress;
}
