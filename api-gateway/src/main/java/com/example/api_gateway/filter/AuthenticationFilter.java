package com.example.api_gateway.filter;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {


    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtFIlter jwtFIlter;

    public static class Config{}

    public AuthenticationFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((serverWebExchange, gatewayFilterChain) -> {
            if(validator.isSecured.test((serverWebExchange.getRequest()))){
                        if(!serverWebExchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                                return unauthorized(serverWebExchange,"Authorization not Available");
                        }
                        String authHeader=serverWebExchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                        if(authHeader!=null && authHeader.startsWith("Bearer ")){
                            String token=authHeader.substring(7);
                            if(!jwtFIlter.validateToken(token)){
                                return unauthorized(serverWebExchange, "Invalid Token");
                            }
                        }

            }
            return gatewayFilterChain.filter(serverWebExchange);
        });
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
    exchange.getResponse().getHeaders().add(HttpHeaders.CONTENT_TYPE, "text/plain");
    byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
    DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
    return exchange.getResponse().writeWith(Mono.just(buffer));
}

}
