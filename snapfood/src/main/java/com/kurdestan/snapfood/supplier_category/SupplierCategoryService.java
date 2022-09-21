package com.kurdestan.snapfood.supplier_category;


import com.kurdestan.snapfood.common.SearchCriteria;
import com.kurdestan.snapfood.common.exception.NotFoundException;
import com.kurdestan.snapfood.supplier.ISupplierService;
import com.kurdestan.snapfood.supplier.Supplier;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SupplierCategoryService implements ISupplierCategoryService{
    private final SupplierCategoryRepository repository;
    private final ISupplierService isupplierService;


    @Override
    public SupplierCategory save(SupplierCategory supplierCategory) {
       Long supplierId=supplierCategory.getId();
      Supplier supplier =isupplierService.getById(supplierId);
      supplierCategory.setSupplier(supplier);
       return repository.save(supplierCategory);
    }

    @Override
    public SupplierCategory update(SupplierCategory supplierCategory) {
        SupplierCategory lastSaveSupplierCategory=getById(supplierCategory.getId());
        lastSaveSupplierCategory.setTitle(supplierCategory.getTitle());
        lastSaveSupplierCategory.setImage(supplierCategory.getImage());
        return repository.save(lastSaveSupplierCategory);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }

    @Override
    public SupplierCategory getById(Long id) {
        Optional<SupplierCategory> optionalSupplierCategory=repository.findById(id);
        if (!optionalSupplierCategory.isPresent()){
            throw new NotFoundException("not found supplierCategory");
        }
        return optionalSupplierCategory.get();
    }

    @Override
    public List<SupplierCategory> getAll() {
        return (List<SupplierCategory>) repository.findAll();
    }

    @Override
    public List<SupplierCategory> getAllBySupplierId(Long supplierId) {
        Supplier supplier=isupplierService.getById(supplierId);
        List<SupplierCategory> supplierCategoryList=repository.findAllBySupplier(supplier);
        return supplierCategoryList;
    }


    @Override
    public Page<SupplierCategory> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page,size, Sort.by("id").descending()));
    }

    @Override
    public List<SupplierCategory> search(List<SearchCriteria> searchCriteria) {
        SupplierCategorySpecification supplierCategorySpecification = new SupplierCategorySpecification();
        searchCriteria.forEach(criteria -> supplierCategorySpecification.add(criteria));
        return repository.findAll(supplierCategorySpecification);
    }
}
