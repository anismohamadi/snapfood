package com.kurdestan.snapfood.user_address;

import com.kurdestan.snapfood.common.BaseDTO;
import com.kurdestan.snapfood.user.User;
import com.kurdestan.snapfood.user.UserDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserAddressDTO extends BaseDTO {

   @ApiModelProperty(required = true,hidden = false)
    private  String title;

    @ApiModelProperty(required = true,hidden = false)
    private String address;

    @ApiModelProperty(required = true,hidden = false)
    private LocationDTO locationDTO;

    @ApiModelProperty(required = true,hidden = false)
    private UserDTO user;
}
