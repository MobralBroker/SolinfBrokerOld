package com.solinfbroker.apigenerica.config;

import com.solinfbroker.apigenerica.config.exceptions.ApiRequestException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Value("${apiAutenticacao.path}")
    private String pathAutenticacao;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        var token = this.recoveryToken(request); 
        try {
            if(token != null){
                ResponseEntity<String> responseAuth = restTemplate.getForEntity(pathAutenticacao+"/auth/validar?token="+token, String.class);
                if(responseAuth.getStatusCode().equals(HttpStatus.UNAUTHORIZED)){
                    throw new ApiRequestException("Token Inválido, refaça o login para liberar o acesso!");
                }
            }else{
                throw new ApiRequestException("Token Inválido, refaça o login para liberar o acesso!");
            }
            filterChain.doFilter(request, response);

        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println("Token Inválido, refaça o login para liberar o acesso!");
        }

    }

    private String recoveryToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader== null) return null;
        return authHeader.replace("Bearer ","");
    }
    
}
