package com.basejava.cart.dto;

import com.basejava.cart.models.User;
import jakarta.validation.constraints.NotNull;

public record CartRequest(

        @NotNull User user
) {
}
