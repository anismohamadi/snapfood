package com.kurdestan.snapfood.user;

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
public class UserService implements IUserService{

    private UserRepository repository;


    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }

    @Override
    public User getById(Long id) {
        Optional<User> optionalUser=repository.findById(id);
        if (!optionalUser.isPresent()){
            throw new NotFoundException(" Not Found User");
        }
        return optionalUser.get();
    }

    @Override
    public List<User> getAll() {
        return (List<User>) repository.findAll();
    }


    @Override
    public Page<User> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page,size, Sort.by("id").descending()));
    }

    @Override
    public List<User> findNearest(Point<G2D> point, double distance) {
        var x= repository. findAllWithDistance(point);
        return repository.findAllWithDistance(point,distance);
    }

    @Override
    public List<User> search(List<SearchCriteria> searchCriteria) {
        UserSpecification userSpecification = new UserSpecification();
        searchCriteria.forEach(criteria -> userSpecification.add(criteria));
        return repository.findAll(userSpecification);
    }
}
