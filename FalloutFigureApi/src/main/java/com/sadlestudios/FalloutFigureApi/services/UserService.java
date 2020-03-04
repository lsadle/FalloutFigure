package com.sadlestudios.FalloutFigureApi.services;

import com.sadlestudios.FalloutFigureApi.models.User;
import com.sadlestudios.FalloutFigureApi.repositories.AddressRepository;
import com.sadlestudios.FalloutFigureApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }
    /**
     * Get one User by its id
     * @param id The id of the User you want
     */
    public User getUser(UUID id) {
        // Get entity
        var entity = userRepository.getOne(id);

        // Get Addresses
        var shippingAddress = addressRepository.getOne(entity.getShippingAddressId());
        var billingAddress = addressRepository.getOne(entity.getBillingAddressId());

        // Create returned User
        var user = new User(entity.getUsername(), shippingAddress.toAddress(), billingAddress.toAddress());
        user.setUserId(entity.getUserId());

        return user;
    }
}
