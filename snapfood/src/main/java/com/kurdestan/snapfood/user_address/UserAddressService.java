package com.kurdestan.snapfood.user_address;

import com.kurdestan.snapfood.category.Category;
import com.kurdestan.snapfood.common.SearchCriteria;
import com.kurdestan.snapfood.common.exception.NotFoundException;
import com.kurdestan.snapfood.supplier.Supplier;
import com.kurdestan.snapfood.supplier.SupplierSpecification;
import com.kurdestan.snapfood.user.IUserService;
import com.kurdestan.snapfood.user.User;
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
public class UserAddressService implements IUserAddressService{
    private final UserAddressRepository repository;
    private final IUserService iUserService;

    @Override
    public UserAddress save(UserAddress userAddress) {
        Long userId=userAddress.getUser().getId();
        User user=iUserService.getById(userId);
        userAddress.setUser(user);
        return repository.save(userAddress);

    }

    @Override
    public UserAddress update(UserAddress userAddress) {
        UserAddress lastSaveUserAddress=getById(userAddress.getId());
        lastSaveUserAddress.setTitle(userAddress.getTitle());
        lastSaveUserAddress.setAddress(userAddress.getAddress());
        lastSaveUserAddress.setLocation(userAddress.getLocation());

        return repository.save(lastSaveUserAddress);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }

    @Override
    public UserAddress getById(Long id) {
        Optional<UserAddress> optionalSupplier=repository.findById(id);
        if (!optionalSupplier.isPresent()){
            throw new NotFoundException("not found supplier");
        }
        return optionalSupplier.get();
    }

    @Override
    public List<UserAddress> getAll()  {
        return (List<UserAddress>) repository.findAll();
    }
    @Override
    public List<UserAddress> getAllByUserId(Long userId) {
        User user=iUserService.getById(userId);
        List<UserAddress> userAddressList=repository.findAllByUser(user);
        return userAddressList;
    }


    @Override
    public Page<UserAddress> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page,size, Sort.by("id").descending()));
    }

    @Override
    public List<UserAddress> findNearest(Point<G2D> point, double distance) {
        var x= repository. findAllWithDistance(point);
        return repository.findAllWithDistance(point,distance);
    }

    @Override
    public List<UserAddress> search(List<SearchCriteria> searchCriteria) {
        UserAddressSpecification userAddressSpecification = new UserAddressSpecification();
        searchCriteria.forEach(criteria -> userAddressSpecification.add(criteria));
        return repository.findAll(userAddressSpecification);
    }
}
