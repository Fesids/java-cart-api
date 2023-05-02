package com.basejava.cart.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public interface GeralUtilities {

    public static String passEncode(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    public static URI toUri(String uri){
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(uri)
                .toString());

    }
}
