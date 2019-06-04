package com.zzpj.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "purchase")
public class Purchase extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "FK__purchase_user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "payment_status_id",
            foreignKey = @ForeignKey(name = "FK__purchase_payment_status"))
    private PaymentStatus paymentStatus;

    @Column(name = "total_price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "shipping_method_id",
            foreignKey = @ForeignKey(name = "FK__purchase_shipping_method"))
    private ShippingMethod shippingMethod;

}
