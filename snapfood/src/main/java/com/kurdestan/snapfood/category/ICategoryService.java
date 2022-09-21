package com.kurdestan.snapfood.category;

import com.kurdestan.snapfood.common.SearchCriteria;
import org.springframework.data.domain.Page;
import java.util.List;


public interface ICategoryService {
    Category save(Category category);
    Category update(Category category);
    void delete(Long id);
    Category getById(Long id);
    List<Category> getAll();
    Page<Category> paging(Integer page, Integer size);
    List<Category> search(List<SearchCriteria> searchCriteria);

}
