package com.solinfbroker.apientidades.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDto( @NotBlank String nome,@NotNull Long valo) {
    
}
