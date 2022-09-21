package com.kurdestan.snapfood.finalize_basket;


import com.kurdestan.snapfood.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FinalizeBasketRepository extends PagingAndSortingRepository<FinalizeBasket, Long> , JpaSpecificationExecutor<FinalizeBasket> {


    List<FinalizeBasket> findAllByUser(User user);
    Page<FinalizeBasket> findAll(Pageable pageable);
    Page<FinalizeBasket> findAll(Specification<FinalizeBasket> specification, Pageable pageable);
    List<FinalizeBasket> findAll(Specification<FinalizeBasket> specification);

}
