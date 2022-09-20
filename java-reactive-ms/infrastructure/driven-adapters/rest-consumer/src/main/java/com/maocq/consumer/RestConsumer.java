package com.maocq.consumer;

import com.maocq.model.hello.gateways.HelloRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.HttpProtocol;
import reactor.netty.http.client.HttpClient;

@Service
public class RestConsumer implements HelloRepository {

    private final WebClient clientNPHttp;
    private final WebClient clientNPHttps;
    private final WebClient clientPool;
    private final WebClient clientHttp2;

    public RestConsumer(
            @Qualifier("noPoolHttp") WebClient clientNPHttp,
            @Qualifier("noPoolHttps") WebClient clientNPHttps,
            @Qualifier("pool")  WebClient clientPool,
            @Qualifier("http2")  WebClient clientHttp2) {
        this.clientNPHttp = clientNPHttp;
        this.clientNPHttps = clientNPHttps;
        this.clientPool = clientPool;
        this.clientHttp2 = clientHttp2;
    }
    @Override
    public Mono<String> helloHttp(int latency) {
        return clientNPHttp
                .get()
                .uri("/{latency}", latency)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public Mono<String> helloHttps(int latency) {
        return clientNPHttps
                .get()
                .uri("/{latency}", latency)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public Mono<String> helloConnectionPoolHttp1(int latency) {
        return clientPool
                .get()
                .uri("/{latency}", latency)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public Mono<String> helloConnectionPoolHttp2(int latency) {
        HttpClient client =
                HttpClient.create()
                        .protocol(HttpProtocol.H2)
                        .secure();

        return client.get()
                        .uri("https://n1.apidevopsteam.xyz/" + latency)
                        .responseSingle((res, bytes) -> bytes.asString());

        /*
        return clientHttp2
                .get()
                .uri("/{latency}", latency)
                .retrieve()
                .bodyToMono(String.class);
        */
    }
}