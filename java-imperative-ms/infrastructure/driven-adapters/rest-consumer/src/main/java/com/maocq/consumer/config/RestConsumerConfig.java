package com.maocq.consumer.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class RestConsumerConfig {

    @Value("${adapter.restconsumer.pool}")
    private int poolSize;

    @Bean(name = "noPool")
    public OkHttpClient getHttpClient() {
        return new OkHttpClient();
    }

    @Bean(name = "pool")
    public OkHttpClient getHttpClientPool() {
        return new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(poolSize,5L, TimeUnit.MINUTES))
                .build();
    }
}
