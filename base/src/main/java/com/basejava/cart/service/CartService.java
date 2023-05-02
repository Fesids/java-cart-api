package com.basejava.cart.service;

import com.basejava.cart.models.Cart;

public interface CartService {

    Cart createCart(long user_id);

    Cart getCart(long user_id);

    void AddBookToCart(long user_id, long book_id);

    void DeleteBookFromCart(long user_id, long book_id);

}
