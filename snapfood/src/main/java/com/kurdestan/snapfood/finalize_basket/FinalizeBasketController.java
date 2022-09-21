package com.kurdestan.snapfood.finalize_basket;

import com.kurdestan.snapfood.common.PagingData;
import com.kurdestan.snapfood.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "finalize_basket")
@AllArgsConstructor

public class FinalizeBasketController {

    private final IFinalizeBasketService iFinalizeBasketService;
    private final FinalizeBasketMapper mapper;

    @PostMapping("/v1")
    public ResponseEntity save(@RequestBody FinalizeBasketDTO finalizeBasketDTO) {
        FinalizeBasket finalizeBasket = mapper.toFinalizeBasket(finalizeBasketDTO);
        iFinalizeBasketService.save(finalizeBasket);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/v1/{id}")
    public  ResponseEntity getById(@PathVariable Long id){
        FinalizeBasket finalizeBasket=iFinalizeBasketService.getById(id);
        FinalizeBasketDTO finalizeBasketDTO=mapper.toFinalizeBasketDTO(finalizeBasket);
        return ResponseEntity.ok(finalizeBasketDTO);
    }

    @GetMapping("/v1")
    public  ResponseEntity<List<FinalizeBasketDTO>> getAll(){
        List<FinalizeBasket> finalizeBasketList=iFinalizeBasketService.getAll();
        List<FinalizeBasketDTO> finalizeBasketDTOS=mapper.toFinalizeBasketDTOs(finalizeBasketList);
        return ResponseEntity.ok(finalizeBasketDTOS);
    }

    @GetMapping("/v1/getByUserAddress/{userAddressId}")
    public  ResponseEntity<List<FinalizeBasketDTO>> getByUserId(@PathVariable Long userId){
        List<FinalizeBasket> finalizeBaskets=iFinalizeBasketService.getAllByUserId(userId);
        List<FinalizeBasketDTO> finalizeBasketDTOS=mapper.toFinalizeBasketDTOs(finalizeBaskets);
        return ResponseEntity.ok(finalizeBasketDTOS);
    }


    @GetMapping("/v1/paging/{page}/{size}")
    public ResponseEntity<PagingData<FinalizeBasket>> getByUser(@PathVariable Integer page, Integer size){
        Page<FinalizeBasket> finalizeBasketPage=iFinalizeBasketService.paging(page,size);
        int totalPage=  finalizeBasketPage.getTotalPages();
        List<FinalizeBasket> data= finalizeBasketPage.getContent();
        PagingData<FinalizeBasket> pagingData=new PagingData<>(totalPage,page,data)  ;
        return ResponseEntity.ok(pagingData);
    }

    @PostMapping(value = "/v1/search")
    public ResponseEntity<List<FinalizeBasketDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<FinalizeBasket> finalizeBaskets= iFinalizeBasketService.search(searchCriteria);
        List<FinalizeBasketDTO> finalizeBasketDTOS = mapper.toFinalizeBasketDTOs(finalizeBaskets);
        return ResponseEntity.ok(finalizeBasketDTOS);
    }

}
