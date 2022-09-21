package com.kurdestan.snapfood.finalize_basket;

import com.kurdestan.snapfood.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IFinalizeBasketService {

    FinalizeBasket save(FinalizeBasket finalizeBasket);
    void delete(Long id);
    FinalizeBasket getById(Long id);
    List<FinalizeBasket> getAll();
    List<FinalizeBasket> getAllByUserId(Long userId);
    Page<FinalizeBasket> paging(Integer page, Integer size);
    List<FinalizeBasket> search(List<SearchCriteria> searchCriteria);
}
