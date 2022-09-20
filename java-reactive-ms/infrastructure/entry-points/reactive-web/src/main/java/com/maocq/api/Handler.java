package com.maocq.api;

import com.maocq.model.account.Account;
import com.maocq.usecase.db.DBUseCase;
import com.maocq.usecase.gethello.GetHelloUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    private final DBUseCase DBUseCase;
    private final GetHelloUseCase getHelloUseCase;

    public Mono<ServerResponse> listenHello(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("Hello");
    }

    public Mono<ServerResponse> listenGETHelloHttp(ServerRequest serverRequest) {
        var latency = serverRequest.queryParam("latency").map(Integer::valueOf).orElse(0);
        return ServerResponse.ok().body(getHelloUseCase.helloHttp(latency), String.class);
    }

    public Mono<ServerResponse> listenGETHelloHttps(ServerRequest serverRequest) {
        var latency = serverRequest.queryParam("latency").map(Integer::valueOf).orElse(0);
        return ServerResponse.ok().body(getHelloUseCase.helloHttps(latency), String.class);
    }

    public Mono<ServerResponse> listenGETHelloConnectionPoolHttp1(ServerRequest serverRequest) {
        var latency = serverRequest.queryParam("latency").map(Integer::valueOf).orElse(0);
        return ServerResponse.ok().body(getHelloUseCase.helloConnectionPoolHttp1(latency), String.class);
    }

    public Mono<ServerResponse> listenGETHelloConnectionPoolHttp2(ServerRequest serverRequest) {
        var latency = serverRequest.queryParam("latency").map(Integer::valueOf).orElse(0);
        return ServerResponse.ok().body(getHelloUseCase.helloConnectionPoolHttp2(latency), String.class);
    }

    public Mono<ServerResponse> listenDB(ServerRequest serverRequest) {
        return ServerResponse.ok().body(DBUseCase.query(), Account.class);
    }
}
