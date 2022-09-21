package com.kurdestan.snapfood.supplier;

import com.kurdestan.snapfood.basket.Basket;
import com.kurdestan.snapfood.category.Category;
import com.kurdestan.snapfood.common.BaseEntity;
import com.kurdestan.snapfood.supplier_category.SupplierCategory;
import lombok.Data;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.hibernate.envers.Audited;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Audited
@Table(name = "tbl_suplier")
@Entity
public class Supplier extends BaseEntity {

    @NotNull
    @Column(name = "title")
    private  String title;

    @NotNull
    @Column(name = "location")
    private Point<G2D> location;

    @NotNull
    @Column(name = "image")
    private  String image;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<SupplierCategory> supplierCategories;


    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Basket> baskets;



}