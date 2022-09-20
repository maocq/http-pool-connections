package com.maocq.usecase.gethello;

import com.maocq.model.hello.gateways.HelloRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetHelloUseCase {
    private final HelloRepository helloRepository;

    public String http(int latency) {
        return helloRepository.http(latency);
    }
    public String https(int latency) {
        return helloRepository.https(latency);
    }

    public String helloConnectionPoolHttp1(int latency) {
        return helloRepository.helloConnectionPoolHttp1(latency);
    }

    public String helloConnectionPoolHttp2(int latency) {
        return helloRepository.helloConnectionPoolHttp2(latency);
    }
}
