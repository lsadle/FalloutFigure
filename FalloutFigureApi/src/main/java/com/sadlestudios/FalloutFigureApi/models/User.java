package com.sadlestudios.FalloutFigureApi.models;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.util.UUID;

@Data
public class User {
    private UUID userId;
    private String password;
    private String username;
    private Address shippingAddress;
    private Address billingAddress;

    private static int workload = 12;

    public User(String username, Address shippingAddress, Address billingAddress) {
        this.username = username;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    public void hashAndSetPassword(String unencryptedPass) {
        password = hashPassword(unencryptedPass);
    }

    public boolean checkPassword(String unencryptedPass) {
        return checkPassword(unencryptedPass, password);
    }

    public static String hashPassword(String unencryptedPass) {
        var hash = BCrypt.gensalt(workload);
        return BCrypt.hashpw(unencryptedPass, hash);
    }

    public static boolean checkPassword(String unencryptedPass, String hashedPassword) {
        return BCrypt.checkpw(unencryptedPass, hashedPassword);
    }
}

