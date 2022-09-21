package com.kurdestan.snapfood.user_address;

import com.kurdestan.snapfood.basket.Basket;
import com.kurdestan.snapfood.common.BaseEntity;
import com.kurdestan.snapfood.user.User;
import com.kurdestan.snapfood.user.UserDTO;
import lombok.Data;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Audited
@Table(name= "tbl_user_address")
@Data
public class UserAddress extends BaseEntity {

    @NotNull
    @Column(name = "title")
    private  String title;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "location")
    private Point<G2D> location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "userAddress", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Basket> baskets;



}



