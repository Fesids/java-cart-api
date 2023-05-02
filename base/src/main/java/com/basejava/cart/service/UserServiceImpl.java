package com.basejava.cart.service;


import com.basejava.cart.dto.UserRequest;
import com.basejava.cart.models.User;
import com.basejava.cart.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserRequest userRequest, String role) {
        var user = (role.equals("ADMIN")?userRequest.addAdmin(userRequest):userRequest.addCostumer(userRequest));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long user_id) {
        return userRepository.findById(user_id).get();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public User getLast() {
        return userRepository.findAll().get(userRepository.findAll().size()-1);
    }


}
