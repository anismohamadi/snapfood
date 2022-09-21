package com.kurdestan.snapfood.supplier_category;


import com.kurdestan.snapfood.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISupplierCategoryService {

    SupplierCategory save(SupplierCategory supplierCategory);
    SupplierCategory update(SupplierCategory supplierCategory);
    void delete(Long id);
    SupplierCategory getById(Long id);
    List<SupplierCategory> getAll();
    List<SupplierCategory> getAllBySupplierId(Long supplierId);
    Page<SupplierCategory> paging(Integer page, Integer size);
    List<SupplierCategory> search(List<SearchCriteria> searchCriteria);
}
