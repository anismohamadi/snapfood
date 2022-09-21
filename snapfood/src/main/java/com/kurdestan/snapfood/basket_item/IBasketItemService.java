package com.kurdestan.snapfood.basket_item;

import com.kurdestan.snapfood.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBasketItemService {
    BasketItem save(BasketItem basketItem);
    void delete(Long id);
    BasketItem getById(Long id);
    List<BasketItem> getAll();
    List<BasketItem> getAllByItemId(Long itemId);
    List<BasketItem> getAllByBasketId(Long basketId);
    Page<BasketItem> paging(Integer page, Integer size);
    List<BasketItem> search(List<SearchCriteria> searchCriteria);
}
