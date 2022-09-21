package com.kurdestan.snapfood.basket_item;

import com.kurdestan.snapfood.basket.Basket;
import com.kurdestan.snapfood.common.BaseEntity;
import com.kurdestan.snapfood.item.Item;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "tbl_basket_item")
@Audited
@Entity
public class BasketItem  extends BaseEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "count")
    private  Integer count;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


}