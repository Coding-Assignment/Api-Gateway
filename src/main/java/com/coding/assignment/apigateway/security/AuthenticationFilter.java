package com.coding.assignment.apigateway.security;


import com.coding.assignment.apigateway.entities.BankUser;
import com.coding.assignment.apigateway.repositories.BankUserRepository;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class AuthenticationFilter implements GlobalFilter {


    private final BankUserRepository bankUserRepository;

    public AuthenticationFilter(BankUserRepository bankUserRepository) {
        this.bankUserRepository = bankUserRepository;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String accessToken = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (accessToken == null) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        Optional<BankUser> bankUser = bankUserRepository.findBankUserByAccessToken(accessToken);

        if (bankUser.isPresent()) {
            return chain.filter(exchange);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}
