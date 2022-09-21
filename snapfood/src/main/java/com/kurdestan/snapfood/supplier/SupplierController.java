package com.kurdestan.snapfood.supplier;

import com.kurdestan.snapfood.common.PagingData;
import com.kurdestan.snapfood.common.SearchCriteria;
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
@RequestMapping(value = "/supplier")
@AllArgsConstructor
public class SupplierController {
    private final ISupplierService iSupplierService;
    private final SupplierMapper mapper;

    @PostMapping("/v1")
     public ResponseEntity save(@RequestBody SupplierDTO supplierDTO){
        Supplier supplier=mapper.toSupplier(supplierDTO);
        iSupplierService.save(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/v1")
    public ResponseEntity update(@RequestBody SupplierDTO supplierDTO){
        Supplier supplier=mapper.toSupplier(supplierDTO);
        iSupplierService.save(supplier);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity delet(@PathVariable Long id){
        iSupplierService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public  ResponseEntity getById(@PathVariable Long id){
        Supplier supplier=iSupplierService.getById(id);
        SupplierDTO supplierDTO=mapper.toSupplierDTO(supplier);
        return ResponseEntity.ok(supplierDTO);
    }

    @GetMapping("/v1")
    public  ResponseEntity<List<SupplierDTO>> getAll(){
        List<Supplier> supplierList=iSupplierService.getAll();
        List<SupplierDTO> supplierDTOS=mapper.toSupplierDTOs(supplierList);
        return ResponseEntity.ok(supplierDTOS);
    }

    @GetMapping("/v1/get_by_categoryId/{id}")
    public  ResponseEntity<List<SupplierDTO>> getByCategoryId(@PathVariable Long categoryId){
        List<Supplier> supplierList=iSupplierService.getAllByCategoryId(categoryId);
        List<SupplierDTO> supplierDTOS=mapper.toSupplierDTOs(supplierList);
        return ResponseEntity.ok(supplierDTOS);
    }

    @GetMapping
    public ResponseEntity<PagingData<Supplier>> getByCategory(@PathVariable Integer page,Integer size){
        Page<Supplier> supplierPage=    iSupplierService.paging(page,size);
        int totalPage=  supplierPage.getTotalPages();
        List<Supplier> data= supplierPage.getContent();
        PagingData<Supplier> pagingData=new PagingData<>(totalPage,page,data)  ;
        return ResponseEntity.ok(pagingData);
    }


    @GetMapping(value = "findNearest/{lat}/{lng}/{distance}")
    public ResponseEntity<List<SupplierDTO>> findNearest(@PathVariable("lat") double lat,@PathVariable("lng") double lng,@PathVariable("distance") double distance) {
        Point<G2D> candidPoint= Geometries.mkPoint(new G2D(lng, lat), CoordinateReferenceSystems.WGS84);
        List<Supplier> suppliers = iSupplierService.findNearest(candidPoint, distance);
        List<SupplierDTO> assetDTOS = mapper.toSupplierDTOs(suppliers);
        return ResponseEntity.ok(assetDTOS);
    }

    @PostMapping(value = "/v1/search")
    public ResponseEntity<List<SupplierDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<Supplier> suppliers= iSupplierService.search(searchCriteria);
        List<SupplierDTO> supplierDTOS = mapper.toSupplierDTOs(suppliers);
        return ResponseEntity.ok(supplierDTOS);
    }


}

