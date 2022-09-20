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

    private final OkHttpClient clientNPHttp;
    private final OkHttpClient clientNPHttps;
    private final OkHttpClient clientPool;
    private final OkHttpClient clientHttp2;

    public RestConsumer(
            @Qualifier("noPoolHttp") OkHttpClient clientNPHttp,
            @Qualifier("noPoolHttps") OkHttpClient clientNPHttps,
            @Qualifier("pool") OkHttpClient clientPool,
            @Qualifier("http2") OkHttpClient clientHttp2
    ) {
        this.clientNPHttp = clientNPHttp;
        this.clientNPHttps = clientNPHttps;
        this.clientPool = clientPool;
        this.clientHttp2 = clientHttp2;
    }

    @Override
    public String http(int latency) {
        Request request = new Request.Builder().url("http://" + url + "/" + latency).get().build();
        try {
            return clientNPHttp.newCall(request).execute().body().string();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String https(int latency) {
        Request request = new Request.Builder().url("https://" + url + "/" + latency).get().build();
        try {
            return clientNPHttps.newCall(request).execute().body().string();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String helloConnectionPoolHttp1(int latency) {
        Request request = new Request.Builder().url("http://" + url + "/" + latency).get().build();
        try {        
            return clientPool.newCall(request).execute().body().string();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String helloConnectionPoolHttp2(int latency) {
        Request request = new Request.Builder().url("https://" + url + "/" + latency).get().build();
        try {
            return clientHttp2.newCall(request).execute().body().string();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}