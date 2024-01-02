package com.solinfbroker.apiautenticacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.solinfbroker.apiautenticacao.model.UsuarioModel;
import com.solinfbroker.apiautenticacao.repository.UsuarioRepository;


@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioModel user = usuarioRepository.findByLogin(username);

        return usuarioRepository.findByLogin(username);
    }
    
    
}
