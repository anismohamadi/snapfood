package com.kurdestan.snapfood.item;

import com.kurdestan.snapfood.common.SearchCriteria;
import com.kurdestan.snapfood.common.exception.NotFoundException;
import com.kurdestan.snapfood.supplier_category.ISupplierCategoryService;
import com.kurdestan.snapfood.supplier_category.SupplierCategory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService implements IItemService{

    private final ItemRepository repository;
    private final ISupplierCategoryService iSupplierCategoryService;

    @Override
    public Item save(Item item) {
        Long supplierCategoryId=item.getSupplierCategory().getId();
        SupplierCategory supplierCategory=iSupplierCategoryService.getById(supplierCategoryId);
        item.setSupplierCategory(supplierCategory);
        return  repository.save(item);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }

    @Override
    public Item getById(Long id) {
        Optional<Item> optionalItem=repository.findById(id);
        if (!optionalItem.isPresent()){
            throw new NotFoundException("Not Found Item");
        }
        return optionalItem.get();
    }

    @Override
    public List<Item> getAll() {
        return (List<Item>) repository.findAll();
    }

    @Override
    public List<Item> getAllBySupplierCategoryId(Long supplierCategoryId) {
        SupplierCategory supplierCategory=iSupplierCategoryService.getById(supplierCategoryId);
        List<Item> itemList=repository.findAllBySupplierCategory(supplierCategory);
        return itemList;
    }

    @Override
    public Page<Item> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page,size, Sort.by("id").descending()));
    }

    @Override
    public List<Item> search(List<SearchCriteria> searchCriteria) {
        ItemSpecification itemSpecification = new ItemSpecification();
        searchCriteria.forEach(criteria -> itemSpecification.add(criteria));
        return repository.findAll(itemSpecification);
    }
}
