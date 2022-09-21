package com.kurdestan.snapfood.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    Page<Category> findAll(Pageable pageable);
    Page<Category> findAll(Specification<Category> specification, Pageable pageable);
    List<Category> findAll(Specification<Category> specification);


}
