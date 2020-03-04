package com.sadlestudios.FalloutFigureApi.entities;

import com.sadlestudios.FalloutFigureApi.models.Address;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "addresses")
public class AddressEntity {
    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private UUID addressId;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    public AddressEntity() { }
    public AddressEntity(String street, String city, String state) {
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public Address toAddress() {
        return new Address(this.addressId, this.street, this.city, this.state);
    }
}
