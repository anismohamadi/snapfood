package com.kurdestan.snapfood.basket;

import com.kurdestan.snapfood.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBasketService {
    Basket save(Basket basket);
    void delete(Long id);
    Basket getById(Long id);
    List<Basket> getAll();
    List<Basket> getAllByUserAddressId(Long userAddressId);
    List<Basket> getAllBySupplierId(Long supplierId);
    Page<Basket> paging(Integer page, Integer size);
    List<Basket> search(List<SearchCriteria> searchCriteria);
}
