package com.maocq.model.hello.gateways;

public interface HelloRepository {

    String hello(int latency);
    String helloConnectionPool(int latency);
}
