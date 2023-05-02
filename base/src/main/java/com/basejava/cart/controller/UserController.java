package com.basejava.cart.controller;

import com.basejava.cart.dto.UserRequest;
import com.basejava.cart.models.Book;
import com.basejava.cart.models.User;
import com.basejava.cart.service.BookService;
import com.basejava.cart.service.UserService;
import com.basejava.cart.utils.GeralUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @PostMapping("/new/{role}")
    public ResponseEntity<User> UserCreate(@RequestBody UserRequest userRequest, @PathVariable("role") String role){
        var uri = GeralUtilities.toUri("/new/{role}");
        return ResponseEntity.created(uri).body(userService.createUser(userRequest, role));
    }

    @GetMapping("/user_books/{user_id}")
    public ResponseEntity<List<Book>> getBookByUserId(@PathVariable("user_id") Long user_id){
        return ResponseEntity.ok().body(bookService.getBooksByUserId(user_id));
    }
}
