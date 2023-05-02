package com.basejava.cart.dto;

import com.basejava.cart.models.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor

@Builder

public class BookRequest{


    @NotEmpty @NotNull String title;

    @NotEmpty @NotNull String description;

    //@NotNull @DecimalMin(value = "0.0") BigDecimal price;

    BigDecimal price;

    @NotEmpty @NotNull String author_book;

    String image;

    User user;
}
