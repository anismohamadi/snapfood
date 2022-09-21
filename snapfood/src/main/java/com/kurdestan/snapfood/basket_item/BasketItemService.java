package com.kurdestan.snapfood.basket_item;



import com.kurdestan.snapfood.basket.Basket;
import com.kurdestan.snapfood.basket.BasketSpecification;
import com.kurdestan.snapfood.basket.IBasketService;
import com.kurdestan.snapfood.common.SearchCriteria;
import com.kurdestan.snapfood.common.exception.NotFoundException;
import com.kurdestan.snapfood.item.IItemService;
import com.kurdestan.snapfood.item.Item;
import com.kurdestan.snapfood.user_address.UserAddress;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class BasketItemService implements IBasketItemService {

    private final BasketItemRepository repository;
    private final IItemService iItemService;
    private final IBasketService iBasketService;


    @Override
    public BasketItem save(BasketItem basketItem) {
        Long itemId=basketItem.getItem().getId();
        Long basketId=basketItem.getBasket().getId();
        Item item=iItemService.getById(itemId);
        Basket basket =iBasketService.getById(basketId);
        basketItem.setBasket(basket);
        basketItem.setItem(item);
        return repository.save(basketItem);


    }

    @Override
    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }

    @Override
    public BasketItem getById(Long id) {
        Optional<BasketItem> optionalBasketItem=repository.findById(id);
        if (!optionalBasketItem.isPresent()){
            throw  new NotFoundException("Not Found Basket");
        }
        return optionalBasketItem.get();
    }

    @Override
    public List<BasketItem> getAll() {
        return (List<BasketItem>) repository.findAll();
    }

    @Override
    public List<BasketItem> getAllByItemId(Long itemId) {
        Item item=iItemService.getById(itemId);
        List<BasketItem> basketsItmes=repository.findAllByItem(item);
        return basketsItmes;
    }

    @Override
    public List<BasketItem> getAllByBasketId(Long basketId) {
        Basket basket=iBasketService.getById(basketId);
        List<BasketItem> basketsItmes=repository.findAllByBasket(basket);
        return basketsItmes;
    }

    @Override
    public Page<BasketItem> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page,size, Sort.by("id").descending()));
    }

    @Override
    public List<BasketItem> search(List<SearchCriteria> searchCriteria) {
        BasketItemSpecification basketItemSpecification = new BasketItemSpecification();
        searchCriteria.forEach(criteria -> basketItemSpecification.add(criteria));
        return repository.findAll(basketItemSpecification);
    }
}