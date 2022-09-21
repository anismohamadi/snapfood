package com.kurdestan.snapfood.supplier;

import com.kurdestan.snapfood.category.CategoryDTO;
import com.kurdestan.snapfood.common.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class SupplierDTO extends BaseDTO {


    @ApiModelProperty(required = true, hidden = false)
    private String title;

    @ApiModelProperty(required = true, hidden = false)
    private LocationDTO locationDTO;

    @ApiModelProperty(required = true, hidden = false)
    private String image;

    @ApiModelProperty(required = true, hidden = false)
    private CategoryDTO category;

}
