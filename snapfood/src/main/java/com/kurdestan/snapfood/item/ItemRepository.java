package com.kurdestan.snapfood.item;

import com.kurdestan.snapfood.supplier_category.SupplierCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ItemRepository extends PagingAndSortingRepository<Item,Long> , JpaSpecificationExecutor<Item> {
    List<Item> findAllBySupplierCategory(SupplierCategory supplierCategory);
    Page<Item> findAll(Pageable pageable);
    Page<Item> findAll(Specification<Item> specification, Pageable pageable);
    List<Item> findAll(Specification<Item> specification);


}

