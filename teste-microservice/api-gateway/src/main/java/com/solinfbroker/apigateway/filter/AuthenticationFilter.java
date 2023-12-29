package com.solinfbroker.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
// import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{
    
    @Autowired
    private RouteValidator routeValidator;

    // @Autowired
    // private RestTemplate restTemplate;

    public AuthenticationFilter(){
        super(Config.class);
    }

    public static class Config{

    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange,chain)->{
            
            if(routeValidator.isSecured.test(exchange.getRequest())){
                // Header possui o token ou não
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("falta o header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader!=null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);
                }

                // chamada de validação do ms Auth
                try {
                    System.out.println("token valido");
                    // restTemplate.getForObject("http://autenticacao/auth/validar?token"+authHeader, String.class);
                } catch (Exception e) {
                    System.out.println("token invalido");
                    throw new RuntimeException("erro de autorização");
                }
            }

            return chain.filter(exchange);
        });
    }
}
