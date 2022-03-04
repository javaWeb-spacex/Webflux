package com.sbx.webflux.annotation.service;

import com.sbx.webflux.annotation.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :sbx
 * @date :2022/3/4 11:32
 * @description :
 * @version: :1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    private  final Map<Integer, User> users=new HashMap<>();
    public UserServiceImpl(){
        this.users.put(1,new User("zhangsan","男",20));
        this.users.put(2,new User("lisi","男",29));
        this.users.put(3,new User("wangwu","女",18));
    }

    @Override
    public Mono<User> getUserById(Integer id) {
        return Mono.justOrEmpty(this.users.get(id));
    }

    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(this.users.values());
    }

    @Override
    public Mono<Void> saveUserInfo(Mono<User> userMono) {
        return userMono.doOnNext(person->{
            this.users.put(this.users.size()+1,person);
        }).thenEmpty(Mono.empty());
    }
}
