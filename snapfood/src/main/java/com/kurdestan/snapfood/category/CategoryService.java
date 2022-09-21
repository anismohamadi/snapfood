package com.kurdestan.snapfood.category;

import com.kurdestan.snapfood.common.SearchCriteria;
import com.kurdestan.snapfood.common.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService{

    private final CategoryRepository repository;


    @Override
    public Category save(Category category) {

        return repository.save(category);
    }

    @Override
    public Category update(Category category) {
        Category lastSaveCategory=getById(category.getId());
        lastSaveCategory.setTitle(category.getTitle());
        lastSaveCategory.setType(category.getType());
        lastSaveCategory.setImage(category.getImage());
        return repository.save(lastSaveCategory);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }

    @Override
    public Category getById(Long id) {
        Optional<Category> optionalCategory=repository.findById(id);
        if (!optionalCategory.isPresent()){
            throw  new NotFoundException("not found category");
        }
        return optionalCategory.get();
    }


    @Override
    public List<Category> getAll() {
        return (List<Category>) repository.findAll();
    }

    @Override
    public Page<Category> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page,size, Sort.by("id").descending()));
    }

    @Override
    public List<Category> search(List<SearchCriteria> searchCriteria) {
        CategorySpecification categorySpecification = new CategorySpecification();
        searchCriteria.forEach(criteria -> categorySpecification.add(criteria));
        return repository.findAll(categorySpecification);
    }


}
