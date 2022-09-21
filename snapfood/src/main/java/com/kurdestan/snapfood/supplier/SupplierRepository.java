package com.kurdestan.snapfood.supplier;


import com.kurdestan.snapfood.category.Category;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometry;
import org.geolatte.geom.Point;
import org.postgresql.core.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends PagingAndSortingRepository<Supplier,Long>, JpaSpecificationExecutor<Supplier> {
    List<Supplier> findAllByCategory(Category category);
    Page<Supplier> findAll(Pageable pageable);
    Page<Supplier> findAll(Specification<Supplier> specification, Pageable pageable);
    List<Supplier> findAll(Specification<Supplier> specification);


    @Query("SELECT vl, distance(vl.location, ?1) as distance from Supplier vl order by distance")
    List<Tuple> findAllWithDistance(Point<G2D> refPnt);


    @Query("SELECT vl from Supplier vl where  distance(vl.location, ?1) < ?2")
    List<Supplier> findAllWithDistance(Point<G2D> refPnt, double dist);


    @Query("SELECT vl, distance(vl.location, ?1) as distance from Supplier vl  order by distance" )
    List<Tuple> findNearest(Point<G2D> refPnt, Pageable page);


    @Query("SELECT v1 FROM Supplier AS v1 WHERE  within(v1.location, :filter)=TRUE ")
    List<Supplier> findWithin(@Param("filter") Geometry filter);


}
