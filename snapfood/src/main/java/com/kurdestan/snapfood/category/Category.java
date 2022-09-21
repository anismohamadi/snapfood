package com.kurdestan.snapfood.category;

import com.kurdestan.snapfood.basket.Basket;
import com.kurdestan.snapfood.common.BaseEntity;
import com.kurdestan.snapfood.supplier.Supplier;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Audited
@Table(name = "tbl_category")
@Entity
public class Category extends BaseEntity {

    @NotNull
    @Column(name = "title")
    private String  title;

    @NotNull
    @Column(name = "image")
    private String  image ;

    @NotNull
    @Enumerated
    @Column(name = "type")
    private  Type type;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Supplier> suppliers;

    @OneToMany(mappedBy = "supplier",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Basket> baskets;



}
