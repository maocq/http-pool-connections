package com.maocq.usecase.gethello;

import com.maocq.model.hello.gateways.HelloRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetHelloUseCase {

    private final HelloRepository helloRepository;

    public Mono<String> hello(int latency) {
        return helloRepository.hello(latency);
    }

    public Mono<String> helloConnectionPool(int latency) {
        return helloRepository.helloConnectionPool(latency);
    }
}
