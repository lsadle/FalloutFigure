package com.sadlestudios.FalloutFigureApi.services;

import com.sadlestudios.FalloutFigureApi.entities.AddressEntity;
import com.sadlestudios.FalloutFigureApi.entities.UserEntity;
import com.sadlestudios.FalloutFigureApi.models.Address;
import com.sadlestudios.FalloutFigureApi.models.LoginCredentials;
import com.sadlestudios.FalloutFigureApi.models.NewUserInfo;
import com.sadlestudios.FalloutFigureApi.models.User;
import com.sadlestudios.FalloutFigureApi.repositories.AddressRepository;
import com.sadlestudios.FalloutFigureApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    /**
     * Creates a new user, and their associated addresses, in the database
     * @param newUser The new user and addresses to make.
     * @return The id of the new user
     */
    public User CreateUser(NewUserInfo newUser) {
        if (newUser == null) {
            return null;
        }

        try {
            // Verify the username is not taken, and the billing address is filled out
            var existingUser = userRepository.findByUsername(newUser.getUsername());
            if (existingUser != null || newUser.getBillingAddress() == null) {
                return null;
            }

            UUID shippingAddressId = null;
            UUID billingAddressId = null;
            Address shippingAddress = null;

            // Create the billing address
            var billingAddress = new AddressEntity(newUser.getBillingAddress().getStreet(), newUser.getBillingAddress().getCity(), newUser.getBillingAddress().getState());
            addressRepository.saveAndFlush(billingAddress);
            billingAddressId = billingAddress.getAddressId();

            // Set the shipping address to the billing address if null
            if (newUser.getShippingAddress() == null) {
                shippingAddressId = billingAddressId;
            } else {
                // Otherwise, create the shipping address
                var shippingAddressEntity = new AddressEntity(newUser.getShippingAddress().getStreet(), newUser.getShippingAddress().getCity(), newUser.getShippingAddress().getState());
                addressRepository.saveAndFlush(shippingAddressEntity);
                shippingAddressId = shippingAddressEntity.getAddressId();
                shippingAddress = shippingAddressEntity.toAddress();
            }

            // Finally, create the new user
            var userEntity = new UserEntity(User.hashPassword(newUser.getPlainTextPassword()), newUser.getUsername(), shippingAddressId, billingAddressId);
            userRepository.saveAndFlush(userEntity);


            var user = new User(userEntity.getUsername(), shippingAddress, billingAddress.toAddress());
            user.setUserId(userEntity.getUserId());
            return user;
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }

    /**
     * Verify a username / password combo exist
     * @param credentials A username / password combination
     * @return Whether or not the combination exists in the database
     */
    public boolean CheckUserPassword(LoginCredentials credentials) {
        // Get the user from the database
        var user = userRepository.findByUsername(credentials.getUsername());
        if (user == null) {
            return false;
        }

        // Verify the password is correct
        return User.checkPassword(credentials.getPassword(), user.getPassword());
    }
}
