package com.kurdestan.snapfood.category;

import com.kurdestan.snapfood.common.SearchCriteria;
import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category/")
@AllArgsConstructor
public class CategoryController {

    private final ICategoryService iCategoryService;
    private final CategoryMapper mapper;

    @PostMapping("/v1")
    public ResponseEntity save(@RequestBody CategoryDTO categoryDTO){
        Category category=mapper.toCategory(categoryDTO);
        iCategoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/v1")
    public ResponseEntity update(@RequestBody CategoryDTO categoryDTO){
        Category category=mapper.toCategory(categoryDTO);
        iCategoryService.save(category);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        iCategoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public  ResponseEntity getById(@PathVariable Long id){
        Category category=iCategoryService.getById(id);
        CategoryDTO categoryDTO=mapper.toCategoryDTO(category);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("/v1")
    public  ResponseEntity<List<CategoryDTO>> getAll(){
        List<Category> categoryList=iCategoryService.getAll();
        List<CategoryDTO> categoryDTOS=mapper.toCategortDTOs(categoryList);
        return ResponseEntity.ok(categoryDTOS);
    }

    @PostMapping(value = "/v1/search")
    public ResponseEntity<List<CategoryDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<Category> categories= iCategoryService.search(searchCriteria);
        List<CategoryDTO> categoryDTOS = mapper.toCategortDTOs(categories);
        return ResponseEntity.ok(categoryDTOS);
    }



}
