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

    public Mono<ServerResponse> listenGETHello(ServerRequest serverRequest) {
        var latency = serverRequest.queryParam("latency").map(Integer::valueOf).orElse(0);
        return ServerResponse.ok().body(getHelloUseCase.hello(latency), String.class);
    }

    public Mono<ServerResponse> listenGETHelloConnectionPool(ServerRequest serverRequest) {
        var latency = serverRequest.queryParam("latency").map(Integer::valueOf).orElse(0);
        return ServerResponse.ok().body(getHelloUseCase.helloConnectionPool(latency), String.class);
    }

    public Mono<ServerResponse> listenDB(ServerRequest serverRequest) {
        return ServerResponse.ok().body(DBUseCase.query(), Account.class);
    }
}
