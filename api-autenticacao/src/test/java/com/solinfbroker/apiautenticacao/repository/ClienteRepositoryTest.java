package com.solinfbroker.apiautenticacao.repository;

import com.solinfbroker.apiautenticacao.dtos.RegisterDTO;
import com.solinfbroker.apiautenticacao.model.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ClienteRepositoryTest {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Deve retornar o usuário com sucesso")
    void findByEmailSuccess() {
        Set<PermissaoModel> permissoes = new HashSet<>();
        Set<PessoaFisica> pessoaFisica = new HashSet<>();
        Set<PessoaJuridica> pessoaJuridica = new HashSet<>();

        ClienteModel clienteModel = new ClienteModel(
                "teste@teste.com",
                "senhaTeste",
                permissoes,
                enumTipoPessoa.PF,
                "usuario teste",
                pessoaFisica,
                pessoaJuridica
        );
        this.criarCliente(clienteModel);

        ClienteModel result = this.clienteRepository.findByEmail(clienteModel.getEmail());

        assertThat(result != null).isTrue();
    }

    private ClienteModel criarCliente(ClienteModel cliente){
        this.entityManager.persist(cliente);
        return cliente;
    }
}