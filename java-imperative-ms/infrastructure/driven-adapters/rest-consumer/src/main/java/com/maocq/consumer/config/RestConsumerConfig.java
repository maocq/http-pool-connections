package com.maocq.consumer.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
public class RestConsumerConfig {

    @Value("${adapter.restconsumer.pool}")
    private int poolSize;

    @Bean(name = "noPoolHttp")
    public OkHttpClient getHttpClient() {
        return new OkHttpClient();
    }


    @Bean(name = "noPoolHttps")
    public OkHttpClient getHttpsClient() {
        return new OkHttpClient();
    }

    @Bean(name = "pool")
    public OkHttpClient getHttp1ClientPool() {
        return new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(poolSize,5L, TimeUnit.MINUTES))
                .build();
    }

    @Bean(name = "http2")
    public OkHttpClient getHttp2ClientPool() {
        return new OkHttpClient.Builder()
                .protocols(Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1))
                .build();
    }
}
