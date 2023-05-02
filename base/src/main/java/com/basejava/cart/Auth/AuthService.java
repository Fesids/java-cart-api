package com.basejava.cart.Auth;

import com.basejava.cart.dto.UserRequest;
import com.basejava.cart.service.UserService;
import com.basejava.cart.config.JwtService;
import com.basejava.cart.config.UserDetail;
import com.basejava.cart.models.User;
import com.basejava.cart.repositories.UserRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetail userDetail;

    public Response register(UserRequest userRequest, String role){
        User user = userService.createUser(userRequest, role);
        String jwt = jwtService.generateToken(user);
        return Response.builder().token(jwt).build();
    }

    public LoginResponse login(AuthRequest authRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
        );
        var user = this.userDetail.loadUserByUsername(authRequest.username());
        var getLoginUser = this.userService.getUserByEmail(authRequest.username());
        String jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder().username(getLoginUser.getUsername())
                .email(getLoginUser.getEmail())
                .id(getLoginUser.getId())
                .token(jwtToken).build();
    }
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Response{
    private String token;
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class LoginResponse{
    private Long id;
    private String email;
    private String username;
    private String token;
}