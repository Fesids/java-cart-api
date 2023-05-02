package com.basejava.cart.Auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record   AuthRequest(
        @NotNull @NotEmpty String username,
        @NotNull @NotEmpty String password
) {
}
