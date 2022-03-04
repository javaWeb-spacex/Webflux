package com.sbx.webflux.function.handler;

import com.sbx.webflux.annotation.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * @author :sbx
 * @date :2022/3/4 19:06
 * @description :
 * @version: :1.0.0
 */
public class Client {
    public static void main(String[] args) {
        //调用服务器地址
        WebClient webClient = WebClient.create("http://127.0.0.1:52461");

        //根据id查询
        String id = "1";
        User block = webClient.get().uri("/user/{id}", id).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(User.class)
                .block();

        System.out.println(block);

        //查询所有
        Flux<User> user = webClient.get().uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(User.class);
        user.map(stu -> stu.getUserName()).buffer().doOnNext(System.out::println).blockFirst();

    }
}
