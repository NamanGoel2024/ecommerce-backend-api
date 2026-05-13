package ecommerce_backend_api.controller;

import ecommerce_backend_api.dto.LoginRequest;
import ecommerce_backend_api.entity.User;
import ecommerce_backend_api.service.UserService;
import org.springframework.web.bind.annotation.*;
import ecommerce_backend_api.dto.LoginRequest;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {

        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {

    return userService.loginUser(
            loginRequest.getEmail(),
            loginRequest.getPassword()
    );
    }
}