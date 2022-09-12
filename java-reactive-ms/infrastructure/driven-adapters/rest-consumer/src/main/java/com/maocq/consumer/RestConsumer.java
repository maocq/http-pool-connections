package com.maocq.consumer;

import com.maocq.model.hello.gateways.HelloRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RestConsumer implements HelloRepository {

    private final WebClient client;
    private final WebClient clientPool;

    public RestConsumer(@Qualifier("noPool") WebClient client, @Qualifier("pool")  WebClient clientPool) {
        this.client = client;
        this.clientPool = clientPool;
    }

    public Mono<String> hello(int latency) {
        return client
            .get()
            .uri("/{latency}", latency)
            .retrieve()
            .bodyToMono(String.class);
    }

    public Mono<String> helloConnectionPool(int latency) {
        return clientPool
                .get()
                .uri("/{latency}", latency)
                .retrieve()
                .bodyToMono(String.class);
    }
}