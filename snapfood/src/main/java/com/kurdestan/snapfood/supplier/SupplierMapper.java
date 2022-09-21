package com.kurdestan.snapfood.supplier;


import com.kurdestan.snapfood.category.CategoryMapper;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.geolatte.geom.G2D;


import java.util.List;



@Mapper(componentModel = "Spring", uses = {CategoryMapper.class})

public interface SupplierMapper {


    @Mappings({
            @Mapping(source = "locationDTO", target = "location", qualifiedByName = "locationDTOToLocation")})
    Supplier toSupplier(SupplierDTO supplierDTO);

    @Mappings({
            @Mapping(source = "location", target = "locationDTO", qualifiedByName = "locationToLocationDTO")})
    SupplierDTO toSupplierDTO(Supplier supplier);
    List<Supplier> toSupplierList(List<SupplierDTO> supplierDTOS);

    List<SupplierDTO> toSupplierDTOs(List<Supplier> supplierList);




    @Named("locationDTOToLocation")
    default Point<G2D> convertLocationDtoToLocation(LocationDTO locationDTO) {
        Point<G2D> candidPoint= Geometries.mkPoint(new G2D(locationDTO.getLng(), locationDTO.getLat()), CoordinateReferenceSystems.WGS84);
        return  candidPoint;
    }

    @Named("locationToLocationDTO")
    default  LocationDTO convertLocationToLocationDto(Point<G2D> point) {
        G2D g2D=   point.getPosition();
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setLat(g2D.getLat());
        locationDTO.setLng(g2D.getLon());
        return  locationDTO;
    }

}

