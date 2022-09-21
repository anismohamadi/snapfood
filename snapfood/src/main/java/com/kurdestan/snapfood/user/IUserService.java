package com.kurdestan.snapfood.user;

import com.kurdestan.snapfood.common.SearchCriteria;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    User save(User user);
    void delete(Long id);
    User getById(Long id);
    List<User> getAll();
    Page<User> paging(Integer page, Integer size);
    List<User> findNearest(Point<G2D> point, double distance);
    List<User> search(List<SearchCriteria> searchCriteria);
}
