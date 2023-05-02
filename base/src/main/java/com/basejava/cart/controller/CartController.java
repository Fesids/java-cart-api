package com.basejava.cart.controller;


import com.basejava.cart.models.Cart;
import com.basejava.cart.repositories.CartRepository;
import com.basejava.cart.service.CartService;
import com.basejava.cart.utils.GeralUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/new_cart/{user_id}")
    public ResponseEntity<Cart> createCart(@PathVariable("user_id") long user_id){
        var newCart = cartService.createCart(user_id);
        var uri = GeralUtilities.toUri("/new_cart");
        return ResponseEntity.created(uri).body(newCart);
    }

    @PostMapping("/add_book_to_cart/{user_id}/{book_id}")
    public ResponseEntity<?> addBookToCart(@PathVariable("user_id") long user_id, @PathVariable("book_id") long book_id){

        cartService.AddBookToCart(user_id, book_id);
        return ResponseEntity.ok().body("adcionou");
    }

    @DeleteMapping("/remove_book_cart/{user_id}/{book_id}")
    public ResponseEntity<?> removeBookFromCart(@PathVariable("user_id") long user_id, @PathVariable("book_id") Long book_id){
        cartService.DeleteBookFromCart(user_id, book_id);
        return ResponseEntity.ok().body("removeu");
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<Cart> getCart(@PathVariable("user_id") long user_id){
        return ResponseEntity.ok().body(cartService.getCart(user_id));
    }

}
