package com.kurdestan.snapfood.supplier;


import com.kurdestan.snapfood.common.SearchCriteria;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISupplierService {
    Supplier save(Supplier supplier);
    Supplier update(Supplier supplier);
    void delete(Long id);
    Supplier getById(Long id);
    List<Supplier> getAll();
    List<Supplier> getAllByCategoryId(Long categoryId);
    Page<Supplier> paging(Integer page, Integer size);
    List<Supplier> findNearest(Point<G2D> point, double distance);
    List<Supplier> search(List<SearchCriteria> searchCriteria);
}
