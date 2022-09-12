package com.maocq.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class RouterRest {
@Bean
public RouterFunction<ServerResponse> routerFunction(Handler handler) {

    return route(GET("/api/hello"), handler::listenHello)
      .andRoute(GET("/api/get-hello"), handler::listenGETHello)
      .andRoute(GET("/api/get-hello-pool"), handler::listenGETHelloConnectionPool)
      .andRoute(GET("/api/db"), handler::listenDB);
    }
}
