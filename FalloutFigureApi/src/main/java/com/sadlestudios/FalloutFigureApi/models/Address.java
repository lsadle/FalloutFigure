package com.sadlestudios.FalloutFigureApi.models;

import lombok.Data;
import java.util.UUID;

@Data
public class Address {
    private UUID addressId;
    private String street;
    private String city;
    private String state;

    public Address() { }

    public Address(UUID addressId, String street, String city, String state) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.state = state;
    }
}
