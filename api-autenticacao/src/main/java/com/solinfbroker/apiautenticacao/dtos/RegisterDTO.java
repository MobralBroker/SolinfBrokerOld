package com.solinfbroker.apiautenticacao.dtos;

import com.solinfbroker.apiautenticacao.model.PermissaoModel;
import com.solinfbroker.apiautenticacao.model.PessoaFisica;
import com.solinfbroker.apiautenticacao.model.PessoaJuridica;
import com.solinfbroker.apiautenticacao.model.enumTipoPessoa;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record RegisterDTO(
        String email,
        String senha,
        Set<PermissaoModel> role,
        enumTipoPessoa tipo,
        String nomeUsuario,
        Set<PessoaFisica> pessoaFisica,
        Set<PessoaJuridica> pessoaJuridica
) {
    
}
