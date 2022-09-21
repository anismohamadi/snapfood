package com.kurdestan.snapfood.supplier_category;

import com.kurdestan.snapfood.supplier.SupplierMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring" , uses = {SupplierMapper.class})
public interface SupplierCategoryMapper {


    SupplierCategory toSupplierCategory(SupplierCategoryDTO supplierCategoryDTO);
    SupplierCategoryDTO toSupplierCategoryDTO(SupplierCategory supplierCategory);
    List<SupplierCategory> toSupplierCategoryList(List<SupplierCategoryDTO> supplierCategoryDTOS);
    List<SupplierCategoryDTO> toSupplierCategortDTOs(List<SupplierCategory> supplierCategoryListcat);
}
