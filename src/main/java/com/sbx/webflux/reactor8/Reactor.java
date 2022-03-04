package com.sbx.webflux.reactor8;

import org.springframework.web.server.WebHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author :sbx
 * @date :2022/3/3 22:15
 * @description :
 * @version: :1.0.0
 */
public class Reactor {
    public static void main(String[] args) {
        //just直接声明
        Flux.just(1,2,3,4);
        Mono.just(1);

        //其他方式
        Integer[] array={2,2,2,2};
        Flux.fromArray(array);

        List list=Arrays.asList(array);
        Flux.fromIterable(list);

        Stream stream=list.stream();
        Flux.fromStream(stream);

        //错误信号
        Flux.error(new Exception());

        //订阅
        Flux.just(1,2,3,4).subscribe(System.out::print);
        Mono.just(1).subscribe(System.out::print);

    }
}
