package com.maocq.usecase.gethello;

import com.maocq.model.hello.gateways.HelloRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetHelloUseCase {
    private final HelloRepository helloRepository;

    public String hello(int latency) {
        return helloRepository.hello(latency);
    }

    public String helloConnectionPool(int latency) {
        return helloRepository.helloConnectionPool(latency);
    }
}
