package com.solinfbroker.apiautenticacao.dtos;

import com.solinfbroker.apiautenticacao.model.PessoaFisica;
import com.solinfbroker.apiautenticacao.model.PessoaJuridica;

import java.util.Set;

public record ClienteModelDTO(
        Long id,
        String email,
        String nomeUsuario,
        double saldo,
        Set<PessoaFisica> pessoaFisica,
        Set<PessoaJuridica> pessoaJuridica
) {
}
