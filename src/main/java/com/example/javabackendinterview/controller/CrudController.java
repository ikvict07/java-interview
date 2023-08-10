package com.example.javabackendinterview.controller;

import com.example.javabackendinterview.RequestResponseBody.UserCreateRequestBody;
import com.example.javabackendinterview.imitaion.MyTimer;
import com.example.javabackendinterview.model.User;
import com.example.javabackendinterview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class CrudController {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/create_user")
    public ResponseEntity<?> createUser(@RequestBody UserCreateRequestBody body) { //Using @Valid to check the validation
        if (userRepository.existsByEmail(body.getEmail())
                || userRepository.existsByName(body.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email or Name already in use");

        }
        User user = new User();
        user.setName(body.getName());
        user.setEmail(body.getEmail());
        String password = body.getPassword();
        if (password == null || password.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password cannot be empty");
        }
        user.setPassword(passwordEncoder.encode(password));

        MyTimer.Imitate();

        userRepository.save(user);
        return ResponseEntity.ok().body(user.getId());
    }

}
