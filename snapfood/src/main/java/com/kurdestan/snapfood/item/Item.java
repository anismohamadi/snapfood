package com.kurdestan.snapfood.item;

import com.kurdestan.snapfood.basket_item.BasketItem;
import com.kurdestan.snapfood.common.BaseEntity;
import com.kurdestan.snapfood.supplier_category.SupplierCategory;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Audited
@Table(name = "tbl_item")
@Entity
public class Item extends BaseEntity {


    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "price")
    private Double price;

    @NotNull
    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "supplier_category_id")
    private SupplierCategory supplierCategory;

    @OneToMany(mappedBy = "item",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BasketItem> basketItems;
}
