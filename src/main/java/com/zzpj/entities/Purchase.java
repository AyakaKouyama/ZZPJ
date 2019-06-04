package com.zzpj.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "FK__purchase_user"))
    private User user;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "payment_status_id",
            foreignKey = @ForeignKey(name = "FK__purchase_payment_status"))
    private PaymentStatus paymentStatus;

    @Column(name = "total_price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "shipping_method_id",
            foreignKey = @ForeignKey(name = "FK__purchase_shipping_method"))
    private ShippingMethod shippingMethod;
}
