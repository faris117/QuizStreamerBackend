package com.example.api_gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;


@Component
public class RouteValidator {


    public final static List<String> openApiEndPoint=List.of(
        "/auth/register",
        "/auth/login",
        "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured= (request)->{
               return openApiEndPoint
                        .stream()
                        .noneMatch(uri->request.getURI().getPath().contains(uri));
    };
}
