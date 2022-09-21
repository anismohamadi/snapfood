package com.kurdestan.snapfood.user;

import com.kurdestan.snapfood.common.SearchCriteria;
import com.kurdestan.snapfood.supplier.Supplier;
import com.kurdestan.snapfood.supplier.SupplierDTO;
import lombok.AllArgsConstructor;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user/")
@AllArgsConstructor
public class UserController {
    private final IUserService iUserService;
    private final UserMapper mapper;

    @PostMapping("/v1")
    public ResponseEntity save(@RequestBody UserDTO userDTO){
        User user=mapper.toUser(userDTO);
        iUserService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/v1")
    public ResponseEntity update(@RequestBody UserDTO userDTO){
        User user=mapper.toUser(userDTO);
        iUserService.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity delet(@PathVariable Long id){
        iUserService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public  ResponseEntity getById(@PathVariable Long id){
        User user=iUserService.getById(id);
        UserDTO userDTO=mapper.toUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/v1")
    public  ResponseEntity<List<UserDTO>> getAll(){
        List<User> userList=iUserService.getAll();
        List<UserDTO> userDTOS=mapper.toUserDTOs(userList);
        return ResponseEntity.ok(userDTOS);
    }


    @GetMapping(value = "findNearest/{lat}/{lng}/{distance}")
    public ResponseEntity<List<UserDTO>> findNearest(@PathVariable("lat") double lat,@PathVariable("lng") double lng,@PathVariable("distance") double distance) {
        Point<G2D> candidPoint= Geometries.mkPoint(new G2D(lng, lat), CoordinateReferenceSystems.WGS84);
        List<User> users = iUserService.findNearest(candidPoint, distance);
        List<UserDTO> assetDTOS = mapper.toUserDTOs(users);
        return ResponseEntity.ok(assetDTOS);
    }

    @PostMapping(value = "/v1/search")
    public ResponseEntity<List<UserDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<User> users= iUserService.search(searchCriteria);
        List<UserDTO> userDTOS = mapper.toUserDTOs(users);
        return ResponseEntity.ok(userDTOS);
    }

}
