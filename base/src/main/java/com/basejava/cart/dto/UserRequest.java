package com.basejava.cart.dto;

import com.basejava.cart.Enum.ERole;
import com.basejava.cart.models.User;
import com.basejava.cart.utils.GeralUtilities;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UserRequest(

        @NotEmpty @NotNull String username,
        @NotEmpty @NotNull @Email String email,
        @NotEmpty @NotNull String password
) {

    public User addCostumer(UserRequest userRequest){
        return User.builder().username(userRequest.username)
                .email(userRequest.email)
                .password(GeralUtilities.passEncode(userRequest.password))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(ERole.COSTUMER)
                .build();
    }

    public User addAdmin(UserRequest userRequest){
        return User.builder().username(userRequest.username)
                .email(userRequest.email)
                .password(GeralUtilities.passEncode(userRequest.password))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(ERole.ADMIN)
                .build();
    }
}
