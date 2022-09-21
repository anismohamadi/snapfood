package com.kurdestan.snapfood.basket;


import com.kurdestan.snapfood.supplier.Supplier;
import com.kurdestan.snapfood.user_address.UserAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BasketRepository extends PagingAndSortingRepository<Basket, Long> , JpaSpecificationExecutor<Basket> {
    List<Basket> findAllByUserAddress(UserAddress userAddress);
    List<Basket> findAllBySupplier(Supplier supplier);
    Page<Basket> findAll(Pageable pageable);
    Page<Basket> findAll(Specification<Basket> specification, Pageable pageable);
    List<Basket> findAll(Specification<Basket> specification);


}
