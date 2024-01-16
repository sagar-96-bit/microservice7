package com.example.microservicedemo.gatewayservice.filter;

import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
@Component
public class ValidateUrl {

    public static final List<String> openApiUrl= List.of("/employee/register","/employee/login","/eureka");
    public Predicate<ServerHttpRequest> isSecured= request -> openApiUrl.stream()
            .noneMatch(uri-> request.getURI().getPath().contains(uri));

}
