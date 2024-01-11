package com.solinfbroker.apiautenticacao.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.solinfbroker.apiautenticacao.dtos.RegisterDTO;
import com.solinfbroker.apiautenticacao.model.*;
import com.solinfbroker.apiautenticacao.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
class AuthenticationServiceTest {

    @Mock
    TokenService tokenService;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    ClienteRepository clienteRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

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

    @Test
    @DisplayName("Registrar novo cliente com falha no email")
    void registrarClienteSemSucessoComFalhaNoEmail() {
        RegisterDTO clienteModel = setupCliente();
//        when()

    }

    @Test
    @DisplayName("Registrar novo cliente com falha no Cpf ou Cnpj")
    void registrarClienteSemSucessoComFalhaNoCpfOuCnpj() {
        RegisterDTO clienteModel = setupCliente();
//        when()

    }

    @Test
    @DisplayName("Verificar se o Cliente cadastrado esta igual ao que foi recebido na requisição")
    void verificarClienteCadastradoIgualAoRecebidoNaRequisicao() {
        RegisterDTO clienteModel = setupCliente();
//        when()

    }
}