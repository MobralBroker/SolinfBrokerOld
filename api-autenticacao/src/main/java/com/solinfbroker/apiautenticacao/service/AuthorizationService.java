package com.solinfbroker.apiautenticacao.service;

import com.solinfbroker.apiautenticacao.model.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.solinfbroker.apiautenticacao.repository.ClienteRepository;


@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClienteModel user = clienteRepository.findByEmail(username);

        return clienteRepository.findByEmail(username);
    }
    
    
}
