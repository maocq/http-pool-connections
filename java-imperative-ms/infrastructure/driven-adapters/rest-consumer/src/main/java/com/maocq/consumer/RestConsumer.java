package com.maocq.consumer;

import com.maocq.model.hello.gateways.HelloRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RestConsumer implements HelloRepository {

    @Value("${adapter.restconsumer.url}")
    private String url;

    private final OkHttpClient client;
    private final OkHttpClient clientPool;

    public RestConsumer(@Qualifier("noPool") OkHttpClient client, @Qualifier("pool") OkHttpClient clientPool) {
        this.client = client;
        this.clientPool = clientPool;
    }


    public String hello(int latency) {
        Request request = new Request.Builder()
            .url(url + "/" + latency)
            .get()
            .build();

        try {
            return client.newCall(request).execute().body().string();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String helloConnectionPool(int latency) {
        Request request = new Request.Builder()
                .url(url + "/" + latency)
                .get()
                .build();

        try {
            return clientPool.newCall(request).execute().body().string();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}