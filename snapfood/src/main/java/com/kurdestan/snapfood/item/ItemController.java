package com.kurdestan.snapfood.item;

import com.kurdestan.snapfood.common.PagingData;
import com.kurdestan.snapfood.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/item/")
@AllArgsConstructor
public class ItemController {

    private final IItemService iItemService;
    private final ItemMapper mapper;

    @PostMapping("/v1")
    public ResponseEntity save(@RequestBody ItemDTO itemDTO) {
        Item item = mapper.toItem(itemDTO);
        iItemService.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        iItemService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public  ResponseEntity getById(@PathVariable Long id){
        Item item=iItemService.getById(id);
        ItemDTO itemDTO=mapper.toItemDTO(item);
        return ResponseEntity.ok(itemDTO);
    }

    @GetMapping("/v1")
    public  ResponseEntity<List<ItemDTO>> getAll(){
        List<Item> itemList=iItemService.getAll();
        List<ItemDTO> itemDTOS=mapper.toItemDTOs(itemList);
        return ResponseEntity.ok(itemDTOS);
    }

    @GetMapping("/v1/get_by_supplier_category_Id/{id}")
    public  ResponseEntity<List<Item>> getBySupplierCategoryId(@PathVariable Long supplierCategoryId){
        List<Item> itemList=iItemService.getAllBySupplierCategoryId(supplierCategoryId);
        List<ItemDTO> itemDTOS=mapper.toItemDTOs(itemList);
        return ResponseEntity.ok(itemList);
    }


    @GetMapping
    public ResponseEntity<PagingData<Item>> getBySupplierCategory(@PathVariable Integer page, Integer size){
        Page<Item> itemPage=iItemService.paging(page,size);
        int totalPage=  itemPage.getTotalPages();
        List<Item> data= itemPage.getContent();
        PagingData<Item> pagingData=new PagingData<>(totalPage,page,data)  ;
        return ResponseEntity.ok(pagingData);
    }

    @PostMapping(value = "/v1/search")
    public ResponseEntity<List<ItemDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<Item> itemList= iItemService.search(searchCriteria);
        List<ItemDTO> itemDTOS = mapper.toItemDTOs(itemList);
        return ResponseEntity.ok(itemDTOS);
    }

}

