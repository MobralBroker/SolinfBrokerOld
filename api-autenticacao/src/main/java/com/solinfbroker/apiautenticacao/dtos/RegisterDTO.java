package com.solinfbroker.apiautenticacao.dtos;

import com.solinfbroker.apiautenticacao.model.PermissaoModel;
import com.solinfbroker.apiautenticacao.model.PessoaFisica;
import com.solinfbroker.apiautenticacao.model.PessoaJuridica;
import com.solinfbroker.apiautenticacao.model.enumTipoPessoa;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Set;

public record RegisterDTO(
        @NotNull
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.8-]+\\.[A-Z|a-z]{2,}$", message = "O campo 'email' deve ser um endereço de e-mail válido")
        String email,
        @NotNull
        String senha,

        Set<PermissaoModel> role,
        @NotNull
        enumTipoPessoa tipo,
        @NotNull
        String nomeUsuario,
        Set<PessoaFisica> pessoaFisica,
        Set<PessoaJuridica> pessoaJuridica
) {
    
}
