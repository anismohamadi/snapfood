package com.kurdestan.snapfood.category;

import com.kurdestan.snapfood.common.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class CategoryDTO extends BaseDTO {

    @ApiModelProperty(required = true,hidden = false)
    private String  title;

    @ApiModelProperty(required = true,hidden = false)
    private String  image ;

    @ApiModelProperty(required = true,hidden = false)
    private  Type type;


}
