package com.kurdestan.snapfood.supplier_category;

import com.kurdestan.snapfood.common.BaseDTO;
import com.kurdestan.snapfood.supplier.SupplierDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SupplierCategoryDTO extends BaseDTO {

    @ApiModelProperty(required = true, hidden = false)
    private String  title;

    @ApiModelProperty(required = true, hidden = false)
    private  String image;


    @ApiModelProperty(required = true, hidden = false)
    private SupplierDTO supplier;

}
