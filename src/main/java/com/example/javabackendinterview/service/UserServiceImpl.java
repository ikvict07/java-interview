package com.example.javabackendinterview.service;

import com.example.javabackendinterview.UserDetailsImpl;
import com.example.javabackendinterview.model.User;
import com.example.javabackendinterview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {
    private UserRepository userRepository;
    @Autowired
    private void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(username).orElseThrow(
                () -> new IllegalArgumentException("No user was found with provided name"));
        return UserDetailsImpl.build(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("No user was found with provided id"));
    }
}
