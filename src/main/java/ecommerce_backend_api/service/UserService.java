package ecommerce_backend_api.service;

import ecommerce_backend_api.entity.User;
import ecommerce_backend_api.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ecommerce_backend_api.security.JwtUtil;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public String loginUser(String email, String password) {

    User user = userRepository.findByEmail(email);

    if(user != null &&
       passwordEncoder.matches(password, user.getPassword())) {

        return JwtUtil.generateToken(user.getEmail());
    }

    return "Invalid Email or Password";
    }

}