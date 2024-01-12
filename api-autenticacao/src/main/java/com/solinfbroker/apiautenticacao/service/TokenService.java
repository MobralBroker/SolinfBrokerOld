package com.solinfbroker.apiautenticacao.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.solinfbroker.apiautenticacao.model.ClienteModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {
    
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(ClienteModel clienteModel){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
            .withIssuer("auth-api")
            .withSubject(clienteModel.getEmail())
            .withExpiresAt(generateExpirationDate())
            .sign(algorithm);
            return token;

        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro geração Token",e);
        }
    }

    private Instant generateExpirationDate(){ //TODO determinar periodo de expiração do token
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken (String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }
}
