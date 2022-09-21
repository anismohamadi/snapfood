package com.kurdestan.snapfood.basket;


import com.kurdestan.snapfood.common.SearchCriteria;
import com.kurdestan.snapfood.common.exception.NotFoundException;
import com.kurdestan.snapfood.supplier.ISupplierService;
import com.kurdestan.snapfood.supplier.Supplier;
import com.kurdestan.snapfood.user_address.IUserAddressService;
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
public class BasketService implements IBasketService{

    private final BasketRepository repository;
    private final IUserAddressService iUserAddressService;
    private final ISupplierService iSupplierService;


    @Override
    public Basket save(Basket basket) {
        Long supplierId=basket.getSupplier().getId();
        Long userAddressId=basket.getUserAddress().getId();
        Supplier supplier=iSupplierService.getById(supplierId);
        UserAddress userAddress =iUserAddressService.getById(userAddressId);
        basket.setSupplier(supplier);
        basket.setUserAddress(userAddress);
        return repository.save(basket);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);

    }

    @Override
    public Basket getById(Long id) {
        Optional<Basket> optionalBasket=repository.findById(id);
        if (!optionalBasket.isPresent()){
            throw  new NotFoundException("Not Found Basket");
        }
        return optionalBasket.get();
    }

    @Override
    public List<Basket> getAll() {
        return (List<Basket>) repository.findAll();
    }

    @Override
    public List<Basket> getAllByUserAddressId(Long userAddressId) {
        UserAddress userAddress=iUserAddressService.getById(userAddressId);
        List<Basket> baskets=repository.findAllByUserAddress(userAddress);
        return baskets;
    }

    @Override
    public List<Basket> getAllBySupplierId(Long supplierId) {
        Supplier supplier=iSupplierService.getById(supplierId);
        List<Basket> baskets=repository.findAllBySupplier(supplier);
        return baskets;
    }

    @Override
    public Page<Basket> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page,size, Sort.by("id").descending()));
    }

    @Override
    public List<Basket> search(List<SearchCriteria> searchCriteria) {
        BasketSpecification basketSpecification = new BasketSpecification();
        searchCriteria.forEach(criteria -> basketSpecification.add(criteria));
        return repository.findAll(basketSpecification);
    }
}
