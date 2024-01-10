package com.solinfbroker.apiautenticacao.service;

import com.solinfbroker.apiautenticacao.dtos.RegisterDTO;
import com.solinfbroker.apiautenticacao.model.*;
import com.solinfbroker.apiautenticacao.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

class AuthenticationServiceTest {

    @Mock
    TokenService tokenService;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    ClienteRepository clienteRepository;

    RegisterDTO setupCliente() {
        PermissaoModel permissaoModel= new PermissaoModel();
        permissaoModel.setId(1L);
        permissaoModel.setPermissao("ROLE_ADMIN");
        Set<PermissaoModel> permissaoModelSet = new HashSet<>();
        permissaoModelSet.add(permissaoModel);

        PessoaFisica pessoaFisica = new PessoaFisica(1L,"11111111111","Pedro", LocalDate.now());
        Set<PessoaFisica> pessoaFisicaSet = new HashSet<>();
        pessoaFisicaSet.add(pessoaFisica);

        Set<PessoaJuridica> pessoaJuridicaSet = new HashSet<>();

        return new RegisterDTO("teste@teste.com","senhaTeste",permissaoModelSet, enumTipoPessoa.PF,"teste",pessoaFisicaSet,pessoaJuridicaSet);
    }

    @Test
    @DisplayName("Registrar novo cliente com sucesso")
    void registrarClienteComSucesso() {
        RegisterDTO clienteModel = setupCliente();
//        when()

    }
}