package com.solinfbroker.apiautenticacao.config;

import java.io.IOException;

import com.solinfbroker.apiautenticacao.model.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.solinfbroker.apiautenticacao.repository.ClienteRepository;
import com.solinfbroker.apiautenticacao.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
    
    @Autowired
    TokenService tokenService;

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        var token = this.recoveryToken(request); 
        if(token != null){
            var email = tokenService.validateToken(token);
            ClienteModel cliente = clienteRepository.findByEmail(email);

            var authentication = new UsernamePasswordAuthenticationToken(cliente, null, cliente.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader== null) return null;
        return authHeader.replace("Bearer ","");
    }
    
}
