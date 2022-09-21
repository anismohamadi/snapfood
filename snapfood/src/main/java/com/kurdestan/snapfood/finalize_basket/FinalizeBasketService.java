package com.kurdestan.snapfood.finalize_basket;

import com.kurdestan.snapfood.common.SearchCriteria;
import com.kurdestan.snapfood.common.exception.NotFoundException;
import com.kurdestan.snapfood.user.IUserService;
import com.kurdestan.snapfood.user.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FinalizeBasketService implements IFinalizeBasketService{

    private final FinalizeBasketRepository repository;
    private final IUserService iUserService;

    @Override
    public FinalizeBasket save(FinalizeBasket finalizeBasket) {
       Long userId= finalizeBasket.getUser().getId();
       User user=iUserService.getById(userId);
       finalizeBasket.setUser(user);
       return repository.save(finalizeBasket);

    }

    @Override
    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }

    @Override
    public FinalizeBasket getById(Long id) {
        Optional<FinalizeBasket> optionalFinalizeBasket=repository.findById(id);
        if (!optionalFinalizeBasket.isPresent()){
            throw  new NotFoundException("Not Found Basket");
        }
        return optionalFinalizeBasket.get();
    }

    @Override
    public List<FinalizeBasket> getAll() {
        return (List<FinalizeBasket>) repository.findAll();
    }

    @Override
    public List<FinalizeBasket> getAllByUserId(Long userId) {
        User user=iUserService.getById(userId);
        List<FinalizeBasket> finalizeBaskets=repository.findAllByUser(user);
        return finalizeBaskets;
    }

    @Override
    public Page<FinalizeBasket> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page,size, Sort.by("id").descending()));
    }

    @Override
    public List<FinalizeBasket> search(List<SearchCriteria> searchCriteria) {
        FinalizeBasketSpecification finalizeBasketSpecification = new FinalizeBasketSpecification();
        searchCriteria.forEach(criteria -> finalizeBasketSpecification.add(criteria));
        return repository.findAll(finalizeBasketSpecification);
    }
}
