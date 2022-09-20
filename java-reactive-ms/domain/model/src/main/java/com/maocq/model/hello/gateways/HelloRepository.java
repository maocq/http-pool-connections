package com.maocq.model.hello.gateways;

import reactor.core.publisher.Mono;

public interface HelloRepository {

    Mono<String> helloHttp(int latency);
    Mono<String> helloHttps(int latency);
    Mono<String> helloConnectionPoolHttp1(int latency);
    Mono<String> helloConnectionPoolHttp2(int latency);
}
