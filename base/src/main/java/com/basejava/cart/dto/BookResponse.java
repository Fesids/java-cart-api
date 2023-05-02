package com.basejava.cart.dto;

import com.basejava.cart.models.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

public class BookResponse {

    private Long id;

    private String title;

    private String description;

    private BigDecimal price;

    private String author_book;

    //@ManyToMany(mappedBy = "books")
    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private User user;

}
