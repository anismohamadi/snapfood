package com.kurdestan.snapfood.basket_item;


import com.kurdestan.snapfood.common.PagingData;
import com.kurdestan.snapfood.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/basketItem")
@AllArgsConstructor
public class BasketItemController {

    private final IBasketItemService iBasketItemService;
    private final BasketItemMapper mapper;


    @PostMapping("/v1")
    public ResponseEntity save(@RequestBody BasketItemDTO basketItemDTO) {
        BasketItem basketItem = mapper.toBasketItem(basketItemDTO);
        iBasketItemService.save(basketItem);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/v1/{id}")
    public  ResponseEntity getById(@PathVariable Long id){
        BasketItem basketItem=iBasketItemService.getById(id);
        BasketItemDTO basketItemDTO=mapper.toBasketItemDTO(basketItem);
        return ResponseEntity.ok(basketItemDTO);
    }

    @GetMapping("/v1")
    public  ResponseEntity<List<BasketItemDTO>> getAll(){
        List<BasketItem> basketItems=iBasketItemService.getAll();
        List<BasketItemDTO> basketItemDTOS=mapper.toBasketItemDTOs(basketItems);
        return ResponseEntity.ok(basketItemDTOS);
    }

    @GetMapping("/v1/getByItem/{itemId}")
    public  ResponseEntity<List<BasketItemDTO>> getByItemId(@PathVariable Long itemId){
        List<BasketItem> basketItems=iBasketItemService.getAllByItemId(itemId);
        List<BasketItemDTO> basketItemDTOS=mapper.toBasketItemDTOs(basketItems);
        return ResponseEntity.ok(basketItemDTOS);
    }


    @GetMapping("/v1/getByBasket/{basketId}")
    public  ResponseEntity<List<BasketItemDTO>> getBySBasketId(@PathVariable Long basketId){
        List<BasketItem> basketItems=iBasketItemService.getAllByBasketId(basketId);
        List<BasketItemDTO> basketItemDTOS=mapper.toBasketItemDTOs(basketItems);
        return ResponseEntity.ok(basketItemDTOS);
    }



    @GetMapping("/v1/paging/{page}/{size}")
    public ResponseEntity<PagingData<BasketItem>> getByItem(@PathVariable Integer page, Integer size){
        Page<BasketItem> basketItemPage=iBasketItemService.paging(page,size);
        int totalPage=  basketItemPage.getTotalPages();
        List<BasketItem> data= basketItemPage.getContent();
        PagingData<BasketItem> pagingData=new PagingData<>(totalPage,page,data)  ;
        return ResponseEntity.ok(pagingData);
    }
    @GetMapping
    public ResponseEntity<PagingData<BasketItem>> getByBasket(@PathVariable Integer page,Integer size){
        Page<BasketItem> basketItemPage=iBasketItemService.paging(page,size);
        int totalPage=  basketItemPage.getTotalPages();
        List<BasketItem> data= basketItemPage.getContent();
        PagingData<BasketItem> pagingData=new PagingData<>(totalPage,page,data)  ;
        return ResponseEntity.ok(pagingData);
    }

    @PostMapping(value = "/v1/search")
    public ResponseEntity<List<BasketItemDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<BasketItem> basketItems= iBasketItemService.search(searchCriteria);
        List<BasketItemDTO> basketItemDTOS = mapper.toBasketItemDTOs(basketItems);
        return ResponseEntity.ok(basketItemDTOS);
    }

}
