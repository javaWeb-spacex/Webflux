package com.sbx.webflux.function.handler;

import com.sbx.webflux.annotation.service.UserService;
import com.sbx.webflux.annotation.service.UserServiceImpl;
import com.sbx.webflux.reactor8.Reactor;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

/**
 * @author :sbx
 * @date :2022/3/4 18:29
 * @description :
 * @version: :1.0.0
 */
public class Server {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.createReactorServer();
        System.out.println("enter to exit");
        System.in.read();
    }

    /**创建Router*/
    public RouterFunction<ServerResponse> router() {
        UserService userService = new UserServiceImpl();
        UserHandler userHandler = new UserHandler(userService);

        return RouterFunctions.route(
                        GET("/user/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::getUserById)
                .andRoute(GET("/users").and(accept(MediaType.APPLICATION_JSON)), userHandler::getAllUser);
    }

    /**创建服务器完成适配*/
    public  void  createReactorServer(){
        //路由和handler适配
        RouterFunction<ServerResponse> router = router();
        HttpHandler httpHandler = toHttpHandler(router);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

        //创建服务器
        HttpServer httpServer=HttpServer.create();
        httpServer.handle(adapter).bindNow();
    }
}
