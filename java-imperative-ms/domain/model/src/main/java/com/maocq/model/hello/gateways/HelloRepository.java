package com.maocq.model.hello.gateways;

public interface HelloRepository {

    String http(int latency);
    String https(int latency);
    String helloConnectionPoolHttp1(int latency);
    String helloConnectionPoolHttp2(int latency);

}
