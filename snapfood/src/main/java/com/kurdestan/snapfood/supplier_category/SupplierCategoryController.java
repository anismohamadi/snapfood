package com.kurdestan.snapfood.supplier_category;


import com.kurdestan.snapfood.common.PagingData;
import com.kurdestan.snapfood.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/supplierCategory")
@AllArgsConstructor
public class SupplierCategoryController {

    private final ISupplierCategoryService iSupplierCategoryService;
    private  final SupplierCategoryMapper mapper;


    @PostMapping("/v1")
    public ResponseEntity save(@RequestBody SupplierCategoryDTO supplierCategoryDTO){
        SupplierCategory supplierCategory=mapper.toSupplierCategory(supplierCategoryDTO);
        iSupplierCategoryService.save(supplierCategory);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/v1")
    public ResponseEntity update(@RequestBody SupplierCategoryDTO supplierCategoryDTO){
        SupplierCategory supplierCategory=mapper.toSupplierCategory(supplierCategoryDTO);
        iSupplierCategoryService.save(supplierCategory);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        iSupplierCategoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public  ResponseEntity getById(@PathVariable Long id){
        SupplierCategory supplierCategory=iSupplierCategoryService.getById(id);
        SupplierCategoryDTO supplierCategoryDTO=mapper.toSupplierCategoryDTO(supplierCategory);
        return ResponseEntity.ok(supplierCategoryDTO);
    }

    @GetMapping("/v1")
    public  ResponseEntity<List<SupplierCategoryDTO>> getAll(){
        List<SupplierCategory> supplierCategoryList=iSupplierCategoryService.getAll();
        List<SupplierCategoryDTO> supplierCategoryDTOS=mapper.toSupplierCategortDTOs(supplierCategoryList);
        return ResponseEntity.ok(supplierCategoryDTOS);
    }

    @GetMapping("/v1/get_by_supplierId/{id}")
    public  ResponseEntity<List<SupplierCategoryDTO>> getBySupplierId(@PathVariable Long supplierId){
        List<SupplierCategory> supplierCategoryList=iSupplierCategoryService.getAllBySupplierId(supplierId);
        List<SupplierCategoryDTO> supplierCategoryDTOS=mapper.toSupplierCategortDTOs(supplierCategoryList);
        return ResponseEntity.ok(supplierCategoryDTOS);
    }

    @GetMapping
    public ResponseEntity<PagingData<SupplierCategory>> getBySupplier(@PathVariable Integer page, Integer size){
        Page<SupplierCategory> supplierCategoryPage=iSupplierCategoryService.paging(page,size);
        int totalPage=  supplierCategoryPage.getTotalPages();
        List<SupplierCategory> data= supplierCategoryPage.getContent();
        PagingData<SupplierCategory> pagingData=new PagingData<>(totalPage,page,data)  ;
        return ResponseEntity.ok(pagingData);
    }

    @PostMapping(value = "/v1/search")
    public ResponseEntity<List<SupplierCategoryDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<SupplierCategory> supplierCategoryList= iSupplierCategoryService.search(searchCriteria);
        List<SupplierCategoryDTO> supplierCategoryDTOS = mapper.toSupplierCategortDTOs(supplierCategoryList);
        return ResponseEntity.ok(supplierCategoryDTOS);
    }



}
