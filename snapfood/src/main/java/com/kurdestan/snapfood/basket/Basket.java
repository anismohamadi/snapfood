package com.kurdestan.snapfood.basket;

import com.kurdestan.snapfood.basket_item.BasketItem;
import com.kurdestan.snapfood.common.BaseEntity;
import com.kurdestan.snapfood.supplier.Supplier;
import com.kurdestan.snapfood.user_address.UserAddress;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Audited
@Table(name = "tbl_basket")
@Entity
public class Basket extends BaseEntity {


@NotNull
@Column(name = "paied_price")
private Double  paiedPrice;



@ManyToOne
@JoinColumn(name = "supplier_id")
 private Supplier supplier;

@ManyToOne
@JoinColumn(name = "user_address_id")
 private UserAddress userAddress;

@OneToMany(mappedBy = "basket", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
 private List<BasketItem> basketItems;

}
