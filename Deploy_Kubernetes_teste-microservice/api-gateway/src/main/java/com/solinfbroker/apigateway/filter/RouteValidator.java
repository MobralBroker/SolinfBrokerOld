package com.solinfbroker.apigateway.filter;

import java.util.*;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;


@Component
public class RouteValidator {

    public static final List<String> openApiEndpoins = List.of(
        "/auth/login",
        "/auth/register",
        "/auth/validar",
        "/eureka"
    );
    
    public Predicate<ServerHttpRequest> isSecured = 
        request-> openApiEndpoins.stream()
        .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
