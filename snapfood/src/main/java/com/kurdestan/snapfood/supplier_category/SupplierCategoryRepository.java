package com.kurdestan.snapfood.supplier_category;

import com.kurdestan.snapfood.supplier.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SupplierCategoryRepository extends PagingAndSortingRepository<SupplierCategory,Long> , JpaSpecificationExecutor<SupplierCategory> {
    List<SupplierCategory> findAllBySupplier(Supplier supplier);
    Page<SupplierCategory> findAll(Pageable pageable);
    Page<SupplierCategory> findAll(Specification<SupplierCategory> specification, Pageable pageable);
    List<SupplierCategory> findAll(Specification<SupplierCategory> specification);

}
