package com.kurdestan.snapfood.basket;


import com.kurdestan.snapfood.common.PagingData;
import com.kurdestan.snapfood.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/basket/")
@AllArgsConstructor
public class BasketController {

    private final IBasketService iBasketService;
    private final BasketMapper mapper;


    @PostMapping("/v1")
    public ResponseEntity save(@RequestBody BasketDTO basketDTO) {
        Basket basket = mapper.toBasket(basketDTO);
        iBasketService.save(basket);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/v1/{id}")
    public  ResponseEntity getById(@PathVariable Long id){
        Basket basket=iBasketService.getById(id);
        BasketDTO basketDTO=mapper.toBasketDTO(basket);
        return ResponseEntity.ok(basketDTO);
    }

    @GetMapping("/v1")
    public  ResponseEntity<List<BasketDTO>> getAll(){
        List<Basket> basketList=iBasketService.getAll();
        List<BasketDTO> basketDTOS=mapper.toBasketDTOs(basketList);
        return ResponseEntity.ok(basketDTOS);
    }

    @GetMapping("/v1/getByUserAddress/{userAddressId}")
    public  ResponseEntity<List<BasketDTO>> getByUserAddressId(@PathVariable Long userAddressId){
        List<Basket> baskets=iBasketService.getAllByUserAddressId(userAddressId);
        List<BasketDTO> basketDTOS=mapper.toBasketDTOs(baskets);
        return ResponseEntity.ok(basketDTOS);
    }


    @GetMapping("/v1/getBySupplier/{supplierId}")
    public  ResponseEntity<List<BasketDTO>> getBySupplierId(@PathVariable Long supplierId){
        List<Basket> baskets=iBasketService.getAllBySupplierId(supplierId);
        List<BasketDTO> basketDTOS=mapper.toBasketDTOs(baskets);
        return ResponseEntity.ok(basketDTOS);
    }

    @GetMapping("/v1/paging/{page}/{size}")
    public ResponseEntity<PagingData<Basket>> getByUserAddress(@PathVariable Integer page, Integer size){
        Page<Basket> basketPage=iBasketService.paging(page,size);
        int totalPage=  basketPage.getTotalPages();
        List<Basket> data= basketPage.getContent();
        PagingData<Basket> pagingData=new PagingData<>(totalPage,page,data)  ;
        return ResponseEntity.ok(pagingData);
    }
    @GetMapping
    public ResponseEntity<PagingData<Basket>> getBySupplier(@PathVariable Integer page,Integer size){
        Page<Basket> basketPage=iBasketService.paging(page,size);
        int totalPage=  basketPage.getTotalPages();
        List<Basket> data= basketPage.getContent();
        PagingData<Basket> pagingData=new PagingData<>(totalPage,page,data)  ;
        return ResponseEntity.ok(pagingData);
    }

    @PostMapping(value = "/v1/search")
    public ResponseEntity<List<BasketDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<Basket> baskets= iBasketService.search(searchCriteria);
        List<BasketDTO> basketDTOS = mapper.toBasketDTOs(baskets);
        return ResponseEntity.ok(basketDTOS);
    }

}
