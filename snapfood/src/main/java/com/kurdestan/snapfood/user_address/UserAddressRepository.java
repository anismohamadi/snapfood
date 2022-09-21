package com.kurdestan.snapfood.user_address;

import com.kurdestan.snapfood.category.Category;
import com.kurdestan.snapfood.supplier.Supplier;
import com.kurdestan.snapfood.user.User;
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

public interface UserAddressRepository extends PagingAndSortingRepository<UserAddress, Long>, JpaSpecificationExecutor<UserAddress> {

    List<UserAddress> findAllByUser(User user);
    Page<UserAddress> findAll(Pageable pageable);
    Page<UserAddress> findAll(Specification<UserAddress> specification, Pageable pageable);
    List<UserAddress> findAll(Specification<UserAddress> specification);


    @Query("SELECT vl, distance(vl.location, ?1) as distance from UserAddress vl order by distance")
    List<Tuple> findAllWithDistance(Point<G2D> refPnt);


    @Query("SELECT vl from UserAddress vl where  distance(vl.location, ?1) < ?2")
    List<UserAddress> findAllWithDistance(Point<G2D> refPnt, double dist);


    @Query("SELECT vl, distance(vl.location, ?1) as distance from UserAddress vl  order by distance" )
    List<Tuple> findNearest(Point<G2D> refPnt, Pageable page);


    @Query("SELECT v1 FROM UserAddress AS v1 WHERE  within(v1.location, :filter)=TRUE ")
    List<UserAddress> findWithin(@Param("filter") Geometry filter);


}
