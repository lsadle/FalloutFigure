package com.sadlestudios.FalloutFigureApi.models;

import lombok.Data;

@Data
public class NewUserInfo {
    private String plainTextPassword;
    private String username;
    private Address shippingAddress;
    private Address billingAddress;
}
