package com.kurdestan.snapfood.user_address;

import com.kurdestan.snapfood.common.SearchCriteria;
import com.kurdestan.snapfood.user.User;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserAddressService {
    UserAddress save(UserAddress userAddress);
    UserAddress update(UserAddress userAddress);
    void delete(Long id);
    UserAddress getById(Long id);
    List<UserAddress> getAll();
    List<UserAddress> getAllByUserId(Long userId);
    Page<UserAddress> paging(Integer page, Integer size);
    List<UserAddress> findNearest(Point<G2D> point, double distance);
    List<UserAddress> search(List<SearchCriteria> searchCriteria);
}
