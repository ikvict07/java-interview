package com.example.javabackendinterview.controller;

import com.example.javabackendinterview.RequestResponseBody.ChangeStatusRequest;
import com.example.javabackendinterview.RequestResponseBody.UserId;
import com.example.javabackendinterview.imitaion.MyTimer;
import com.example.javabackendinterview.model.User;
import com.example.javabackendinterview.model.UserStatus;
import com.example.javabackendinterview.repository.UserRepository;
import com.example.javabackendinterview.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ProtectedController {
    UserRepository userRepository;
    UserServiceImpl userService;

    @Autowired
    private void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
    @Autowired
    private void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @GetMapping("/info")
    public User getUser(@RequestBody UserId id){

        MyTimer.Imitate();

        return userService.getUserById(id.getId());
    }
    @PostMapping("/change-status")
    public ResponseEntity<?> changeStatus(@RequestBody ChangeStatusRequest request){
        User user = userService.getUserById(request.getId());
        UserStatus previousStatus = user.getStatus();
        user.setStatus(request.getStatus());

        MyTimer.Imitate();

        userRepository.save(user);
        return ResponseEntity.ok().body(user.getId() + " " + previousStatus + " " +  user.getStatus());
    }
}
