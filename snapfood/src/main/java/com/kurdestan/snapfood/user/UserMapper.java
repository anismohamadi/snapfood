package com.kurdestan.snapfood.user;


import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "locationDTO", target = "location", qualifiedByName = "locationDTOToLocation")})
    User toUser(UserDTO userDTO);

    @Mappings({
            @Mapping(source = "location", target = "locationDTO", qualifiedByName = "locationToLocationDTO")})
    UserDTO toUserDTO(User user);
    List<User> toUserList(List<UserDTO> userDTOS);

    List<UserDTO> toUserDTOs(List<User> userList);






    @Named("locationDTOToLocation")
    default Point<G2D> convertLocationDtoToLocation(com.kurdestan.snapfood.user.LocationDTO locationDTO) {
        Point<G2D> candidPoint= Geometries.mkPoint(new G2D(locationDTO.getLng(), locationDTO.getLat()), CoordinateReferenceSystems.WGS84);
        return  candidPoint;
    }

    @Named("locationToLocationDTO")
    default com.kurdestan.snapfood.user.LocationDTO convertLocationToLocationDto(Point<G2D> point) {
        G2D g2D=   point.getPosition();
        com.kurdestan.snapfood.user.LocationDTO locationDTO=new com.kurdestan.snapfood.user.LocationDTO();
        locationDTO.setLat(g2D.getLat());
        locationDTO.setLng(g2D.getLon());
        return  locationDTO;
    }
}
