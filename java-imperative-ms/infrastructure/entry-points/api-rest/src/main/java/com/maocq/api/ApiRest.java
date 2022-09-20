package com.maocq.api;

import com.maocq.model.account.Account;
import com.maocq.usecase.db.DBUseCase;
import com.maocq.usecase.gethello.GetHelloUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {

    private final DBUseCase DBUseCase;
    private final GetHelloUseCase helloUseCase;

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping(path = "/http")
    public String getHelloHttp(@RequestParam(required = false) Integer latency) {
        var latencyValue = latency == null ? 0 : latency;
        return helloUseCase.http(latencyValue);
    }

    @GetMapping(path = "/https")
    public String getHelloHttps(@RequestParam(required = false) Integer latency) {
        var latencyValue = latency == null ? 0 : latency;
        return helloUseCase.https(latencyValue);
    }

    @GetMapping(path = "/pool-http1")
    public String getHelloConnectionPoolHttp1(@RequestParam(required = false) Integer latency) {
        var latencyValue = latency == null ? 0 : latency;
        return helloUseCase.helloConnectionPoolHttp1(latencyValue);
    }

    @GetMapping(path = "/pool-http2")
    public String getHelloConnectionPoolHttp2(@RequestParam(required = false) Integer latency) {
        var latencyValue = latency == null ? 0 : latency;
        return helloUseCase.helloConnectionPoolHttp2(latencyValue);
    }

    @GetMapping(path = "/db")
    public Optional<Account> db() {
        return DBUseCase.query();
    }
}
