package com.sadlestudios.FalloutFigureApi.entities;

import com.sadlestudios.FalloutFigureApi.models.User;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private UUID userId;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "SHIPPING_ADDRESS")
    private UUID shippingAddressId;

    @Column(name = "BILLING_ADDRESS")
    private UUID billingAddressId;

    public UserEntity() { }
    public UserEntity(String password, String username, UUID shippingAddressId, UUID billingAddressId) {
        this.password = password;
        this.username = username;
        this.shippingAddressId = shippingAddressId;
        this.billingAddressId = billingAddressId;
    }
}

