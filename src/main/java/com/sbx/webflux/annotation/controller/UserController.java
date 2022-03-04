package com.sbx.webflux.annotation.controller;

import com.sbx.webflux.annotation.entity.User;
import com.sbx.webflux.annotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author :sbx
 * @date :2022/3/4 11:45
 * @description :
 * @version: :1.0.0
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    Mono<User> getUserById(@PathVariable Integer id){
        return  userService.getUserById(id);
    }

    @GetMapping("/user")
    Flux<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/saveUser")
    Mono<Void> saveUserInfo(@RequestBody User user){
        Mono<User> userMono=Mono.just(user);
        return  userService.saveUserInfo(userMono);
    }


}
