package com.basejava.cart.models;

import com.basejava.cart.dto.CartRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "user_cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(name = "cart_books", nullable = true, updatable = true)
    private List<Book> cart_books;

    public Cart(CartRequest cartRequest){

        this.user = cartRequest.user();
    }
}


