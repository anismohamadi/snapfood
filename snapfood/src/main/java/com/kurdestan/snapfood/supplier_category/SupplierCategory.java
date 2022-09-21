package com.kurdestan.snapfood.supplier_category;

import com.kurdestan.snapfood.common.BaseEntity;
import com.kurdestan.snapfood.item.Item;
import com.kurdestan.snapfood.supplier.Supplier;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Audited
@Table(name = "tbl_suplier_category")
@Entity
public class SupplierCategory extends BaseEntity {

    @NotNull
    @Column(name = "title")
    private String  title;

    @NotNull
    @Column(name = "image")
    private  String image;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "supplierCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> itemList;

}
