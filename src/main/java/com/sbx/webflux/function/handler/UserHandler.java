package com.sbx.webflux.function.handler;

import com.sbx.webflux.annotation.entity.User;
import com.sbx.webflux.annotation.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @author :sbx
 * @date :2022/3/4 11:45
 * @description :
 * @version: :1.0.0
 */

public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService){
        this.userService=userService;
    }

    public Mono<ServerResponse> getUserById(ServerRequest request){
        //获取id值
        int id =Integer.valueOf(request.pathVariable("id"));

        //空值处理
        Mono<ServerResponse> notFound=ServerResponse.notFound().build();

        //调用service方法得到数据
        Mono<User> userMono = userService.getUserById(id);

        //把userMono进行转换返回
        //使用Reactor操作符flatMap
        return userMono.
                flatMap((person-> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(person))))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> getAllUser(ServerRequest request){
        Flux<User> allUser = this.userService.getAllUser();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(allUser,User.class);
    }


    public Mono<ServerResponse> saveUserInfo( ServerRequest request){
        //得到User对象
        Mono<User> user =request.bodyToMono(User.class);

        return ServerResponse.ok().build(this.userService.saveUserInfo(user));
    }


}
