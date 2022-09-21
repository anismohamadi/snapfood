package com.kurdestan.snapfood.user;


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


public interface UserRepository extends PagingAndSortingRepository<User,Long>, JpaSpecificationExecutor<User> {
    Page<User> findAll(Pageable pageable);
    Page<User> findAll(Specification<User> specification, Pageable pageable);
    List<User> findAll(Specification<User> specification);

    @Query("SELECT vl, distance(vl.location, ?1) as distance from User vl order by distance")
    List<Tuple> findAllWithDistance(Point<G2D> refPnt);


    @Query("SELECT vl from User vl where  distance(vl.location, ?1) < ?2")
    List<User> findAllWithDistance(Point<G2D> refPnt, double dist);


    @Query("SELECT vl, distance(vl.location, ?1) as distance from User vl  order by distance" )
    List<Tuple> findNearest(Point<G2D> refPnt, Pageable page);


    @Query("SELECT v1 FROM User AS v1 WHERE  within(v1.location, :filter)=TRUE ")
    List<User> findWithin(@Param("filter") Geometry filter);
}
