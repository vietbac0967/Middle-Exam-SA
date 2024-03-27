package com.bac.se.registerservice.controllers;

import com.bac.se.registerservice.models.User;
import com.bac.se.registerservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not Found User"));
    }

    @GetMapping
    public User getUserByUsernameAndPassword(@RequestParam("username") String username,
                                             @RequestParam("password") String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new RuntimeException("Not found user"));
    }
}
