package com.solinfbroker.apiautenticacao.dtos;

import com.solinfbroker.apiautenticacao.model.PermissaoModel;
import com.solinfbroker.apiautenticacao.model.PessoaFisica;
import com.solinfbroker.apiautenticacao.model.PessoaJuridica;

import java.util.Set;

public record RegisterDTO(
        String email,
        String senha,
        Set<PermissaoModel> role,
        String tipo,
        String nomeUsuario,
        Set<PessoaFisica> pessoaFisica,
        Set<PessoaJuridica> pessoaJuridica
) {
    
}
