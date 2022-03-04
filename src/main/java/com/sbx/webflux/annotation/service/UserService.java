package com.sbx.webflux.annotation.service;

import com.sbx.webflux.annotation.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author :sbx
 * @date :2022/3/4 11:29
 * @description :
 * @version: :1.0.0
 */
public interface UserService {
    /**根据id查询用户*/
    Mono<User> getUserById(Integer id);

    /**查询所有用户*/
    Flux<User> getAllUser();

    /**添加用户*/
    Mono<Void> saveUserInfo(Mono<User> user);

}
