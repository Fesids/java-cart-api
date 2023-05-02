package com.basejava.cart.config;

import com.basejava.cart.exceptions.UserNotFoundException;
import com.basejava.cart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetail implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username)
                .or(
                        () -> userRepository.findByUsername(username)
                ).orElseThrow(
                        ()-> new UserNotFoundException("usuario nao encontrado")
                );
    }
}










