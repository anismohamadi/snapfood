package com.kurdestan.snapfood.user_address;

import com.kurdestan.snapfood.common.PagingData;
import com.kurdestan.snapfood.common.SearchCriteria;
import com.kurdestan.snapfood.supplier.Supplier;
import com.kurdestan.snapfood.supplier.SupplierDTO;
import lombok.AllArgsConstructor;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user_address")
@AllArgsConstructor
public class UserAddressController {
    private final IUserAddressService iUserAddressService;
    private final UserAddressMapper mapper;

    @PostMapping("/v1")
    public ResponseEntity save(@RequestBody UserAddressDTO userAddressDTO){
        UserAddress userAddress=mapper.toUserAddress(userAddressDTO);
        iUserAddressService.save(userAddress);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/v1")
    public ResponseEntity update(@RequestBody UserAddressDTO userAddressDTO){
        UserAddress userAddress=mapper.toUserAddress(userAddressDTO);
        iUserAddressService.save(userAddress);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity delet(@PathVariable Long id){
        iUserAddressService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public  ResponseEntity getById(@PathVariable Long id){
        UserAddress userAddress=iUserAddressService.getById(id);
        UserAddressDTO userAddressDTO=mapper.toUserAddressDTO(userAddress);
        return ResponseEntity.ok(userAddressDTO);
    }

    @GetMapping("/v1")
    public  ResponseEntity<List<UserAddressDTO>> getAll(){
        List<UserAddress> userAddressList=iUserAddressService.getAll();
        List<UserAddressDTO> userAddressDTOS=mapper.toUserAddressDTOs(userAddressList);
        return ResponseEntity.ok(userAddressDTOS);
    }

    @GetMapping("/v1/get_by_categoryId/{id}")
    public  ResponseEntity<List<UserAddressDTO>> getByUserId(@PathVariable Long userId){
        List<UserAddress> userAddressList=iUserAddressService.getAllByUserId(userId);
        List<UserAddressDTO> userAddressDTOS=mapper.toUserAddressDTOs(userAddressList);
        return ResponseEntity.ok(userAddressDTOS);
    }

    @GetMapping
    public ResponseEntity<PagingData<UserAddress>> getByUser(@PathVariable Integer page, Integer size){
        Page<UserAddress> userAddressPage=iUserAddressService.paging(page,size);
        int totalPage=  userAddressPage.getTotalPages();
        List<UserAddress> data= userAddressPage.getContent();
        PagingData<UserAddress> pagingData=new PagingData<>(totalPage,page,data)  ;
        return ResponseEntity.ok(pagingData);
    }


    @GetMapping(value = "findNearest/{lat}/{lng}/{distance}")
    public ResponseEntity<List<UserAddressDTO>> findNearest(@PathVariable("lat") double lat,@PathVariable("lng") double lng,@PathVariable("distance") double distance) {
        Point<G2D> candidPoint= Geometries.mkPoint(new G2D(lng, lat), CoordinateReferenceSystems.WGS84);
        List<UserAddress> userAddressList = iUserAddressService.findNearest(candidPoint, distance);
        List<UserAddressDTO> assetDTOS = mapper.toUserAddressDTOs(userAddressList);
        return ResponseEntity.ok(assetDTOS);
    }

    @PostMapping(value = "/v1/search")
    public ResponseEntity<List<UserAddressDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<UserAddress> userAddressList= iUserAddressService.search(searchCriteria);
        List<UserAddressDTO> userAddressDTOS = mapper.toUserAddressDTOs(userAddressList);
        return ResponseEntity.ok(userAddressDTOS);
    }



}
