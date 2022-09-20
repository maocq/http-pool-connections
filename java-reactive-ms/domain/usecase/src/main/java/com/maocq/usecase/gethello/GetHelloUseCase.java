package com.maocq.usecase.gethello;

import com.maocq.model.hello.gateways.HelloRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetHelloUseCase {

    private final HelloRepository helloRepository;

    public Mono<String> helloHttp(int latency) {
        return helloRepository.helloHttp(latency);
    }

    public Mono<String> helloHttps(int latency) {
        return helloRepository.helloHttps(latency);
    }

    public Mono<String> helloConnectionPoolHttp1(int latency) {
        return helloRepository.helloConnectionPoolHttp1(latency);
    }

    public Mono<String> helloConnectionPoolHttp2(int latency) {
        return helloRepository.helloConnectionPoolHttp2(latency);
    }
}
