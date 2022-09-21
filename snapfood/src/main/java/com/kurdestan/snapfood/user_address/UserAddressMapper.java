package com.kurdestan.snapfood.user_address;


import com.kurdestan.snapfood.user.UserMapper;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "Spring", uses = {UserMapper.class})
public interface UserAddressMapper {
    @Mappings({
            @Mapping(source = "locationDTO", target = "location", qualifiedByName = "locationDTOToLocation")})
    UserAddress toUserAddress(UserAddressDTO userAddressDTO);

    @Mappings({
            @Mapping(source = "location", target = "locationDTO", qualifiedByName = "locationToLocationDTO")})
    UserAddressDTO toUserAddressDTO(UserAddress userAddress);
    List<UserAddress> toUserAddressList(List<UserAddressDTO> userAddressDTOS);

    List<UserAddressDTO> toUserAddressDTOs(List<UserAddress> userAddressList);





    @Named("locationDTOToLocation")
    default Point<G2D> convertLocationDtoToLocation(com.kurdestan.snapfood.user_address.LocationDTO locationDTO) {
        Point<G2D> candidPoint= Geometries.mkPoint(new G2D(locationDTO.getLng(), locationDTO.getLat()), CoordinateReferenceSystems.WGS84);
        return  candidPoint;
    }

    @Named("locationToLocationDTO")
    default com.kurdestan.snapfood.user_address.LocationDTO convertLocationToLocationDto(Point<G2D> point) {
        G2D g2D=   point.getPosition();
        com.kurdestan.snapfood.user_address.LocationDTO locationDTO=new com.kurdestan.snapfood.user_address.LocationDTO();
        locationDTO.setLat(g2D.getLat());
        locationDTO.setLng(g2D.getLon());
        return  locationDTO;
    }
}
