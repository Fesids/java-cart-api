package com.basejava.cart.service;


import com.basejava.cart.dto.UserRequest;
import com.basejava.cart.models.User;

public interface UserService {

    public User createUser(UserRequest userRequest, String role);

    User getUserById(Long user_id);

    User getUserByEmail(String email);

    User getLast();

}
