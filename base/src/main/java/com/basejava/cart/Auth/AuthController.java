package com.basejava.cart.Auth;

import com.basejava.cart.dto.CartRequest;
import com.basejava.cart.dto.UserRequest;
import com.basejava.cart.models.User;
import com.basejava.cart.service.CartService;
import com.basejava.cart.service.UserService;
import com.basejava.cart.utils.GeralUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private WebClient.Builder webClient;

    @PostMapping("/register/{role}")
    public ResponseEntity<Response> register(@RequestBody UserRequest userRequest, @PathVariable String role){
        URI uri = GeralUtilities.toUri("/register/{role}");


        var token = authService.register(userRequest, role);

        var u = userService.getLast();
        var cart = new CartRequest(u);
        this.webClient.build().post()
                .uri("http://localhost:8081/api/cart/new_cart/"+u.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(Mono.just(cart), CartRequest.class))
                .exchangeToMono(response ->{
                    if(response.statusCode().equals(HttpStatus.CREATED)){
                        return response.bodyToMono(CartRequest.class);
                    } else {
                        return response.createException().flatMap(Mono::error);
                    }
                }).block();
    /*
        var profile = ProfileRequest.builder().email(u.getEmail())
                .user(u)
                .first_name(u.getUsername())
                .last_name(" ")
                .city(" ")
                .img(" ").build();

        /*this.webClient.build().post()
                .uri("http://localhost:8081/api/profile/newt")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(Mono.just(profile), ProfileRequest.class))
                .exchangeToMono(response ->{
                    if(response.statusCode().equals(HttpStatus.CREATED)){
                        return response.bodyToMono(ProfileRequest.class);
                    } else {
                        return response.createException().flatMap(Mono::error);
                    }
                }).block();*/
        return ResponseEntity.created(uri).body(token);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok().body(authService.login(authRequest));
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(userService.getUserByEmail(email));

    }

}












