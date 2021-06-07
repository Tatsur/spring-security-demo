package com.ttsr.springsecuritydemo.controller;

import com.ttsr.springsecuritydemo.entity.User;
import com.ttsr.springsecuritydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@Profile("dao")
@Slf4j
@SessionScope
@RequiredArgsConstructor
@RequestMapping("/score")
public class ScoreController {

    private final UserService userService;

    @GetMapping
    public User getUser(Principal principal){
        return getUserByPrincipal(principal);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(()->new NoSuchElementException(String.format("User with name: %s doesn't exist", username)));
        log.info("user: " + user.getUsername());
        return user;
    }

    @GetMapping("/get/{id}")
    public int getScoreById(@PathVariable Long id){
        User user = userService
                .findById(id)
                .orElseThrow(()->new NoSuchElementException(String.format("User with id: %s doesn't exist",id)));
        return user.getScore();
    }

    @GetMapping("get/current")
    public int getScore(Principal principal){
        User user = getUserByPrincipal(principal);
        return user.getScore();
    }

    @PostMapping("/inc")
    public int increment(Principal principal){
        User user = getUserByPrincipal(principal);
        user.setScore(user.getScore() + 1);
        return user.getScore();
    }

    @PostMapping("/dec")
    public int decrement(Principal principal){
        User user = getUserByPrincipal(principal);
        user.setScore(user.getScore() - 1);
        return user.getScore();
    }
}
