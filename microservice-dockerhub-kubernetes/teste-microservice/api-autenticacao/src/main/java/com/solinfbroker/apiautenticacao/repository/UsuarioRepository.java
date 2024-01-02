package com.solinfbroker.apiautenticacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.solinfbroker.apiautenticacao.model.UsuarioModel;


@Service
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
    
    UsuarioModel findByLogin(String login);
}
