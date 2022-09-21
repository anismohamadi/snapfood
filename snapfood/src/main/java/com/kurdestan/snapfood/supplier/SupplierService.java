package com.kurdestan.snapfood.supplier;

import com.kurdestan.snapfood.category.Category;

import com.kurdestan.snapfood.category.ICategoryService;
import com.kurdestan.snapfood.common.SearchCriteria;
import com.kurdestan.snapfood.common.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SupplierService implements  ISupplierService{
    private final SupplierRepository repository;
    private final ICategoryService iCategoryService;



    @Override
    public Supplier save(Supplier supplier) {
        Long categoryId=supplier.getCategory().getId();
        Category category=iCategoryService.getById(categoryId);
        supplier.setCategory(category);
        return repository.save(supplier);
    }

    @Override
    public Supplier update(Supplier supplier) {
        Supplier lastSaveSupplier=getById(supplier.getId());
        lastSaveSupplier.setTitle(supplier.getTitle());
        lastSaveSupplier.setImage(supplier.getImage());
        lastSaveSupplier.setLocation(supplier.getLocation());

        return repository.save(lastSaveSupplier);

    }

    @Override
    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);

    }

    @Override
    public Supplier getById(Long id) {
        Optional<Supplier> optionalSupplier=repository.findById(id);
        if (!optionalSupplier.isPresent()){
            throw new NotFoundException("not found supplier");
        }
        return optionalSupplier.get();
    }

    @Override
    public List<Supplier> getAll() {
        return (List<Supplier>) repository.findAll();
    }

    @Override
    public List<Supplier> getAllByCategoryId(Long categoryId) {
        Category category=iCategoryService.getById(categoryId);
        List<Supplier> supplierList=repository.findAllByCategory(category);
        return supplierList;
    }


    @Override
    public Page<Supplier> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page,size, Sort.by("id").descending()));
    }

    @Override
    public List<Supplier> findNearest(Point<G2D> point, double distance) {
        var x= repository. findAllWithDistance(point);
        return repository.findAllWithDistance(point,distance);
    }

    @Override
    public List<Supplier> search(List<SearchCriteria> searchCriteria) {
        SupplierSpecification supplierSpecification = new SupplierSpecification();
        searchCriteria.forEach(criteria -> supplierSpecification.add(criteria));
        return repository.findAll(supplierSpecification);
    }
}
