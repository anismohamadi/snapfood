package com.kurdestan.snapfood.item;

import com.kurdestan.snapfood.common.BaseDTO;
import com.kurdestan.snapfood.supplier_category.SupplierCategoryDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ItemDTO extends BaseDTO {

    @ApiModelProperty(required = true,hidden = false)
    private String name;

    @ApiModelProperty(required = true,hidden = false)
    private Double price;

    @ApiModelProperty(required = true,hidden = false)
    private String image;

    @ApiModelProperty(required = true,hidden = false)
    private SupplierCategoryDTO supplierCategory;

}

