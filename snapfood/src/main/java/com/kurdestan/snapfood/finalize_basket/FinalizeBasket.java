package com.kurdestan.snapfood.finalize_basket;

import com.kurdestan.snapfood.common.BaseEntity;
import com.kurdestan.snapfood.user.User;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "tbl_finalize_basket")
@Audited
public class FinalizeBasket extends BaseEntity {

    @NotNull
    @Column(name = "status")
    private Status status;

    @NotNull
    @Column(name = "paid_price")
    private  Double paidPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
