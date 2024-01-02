package com.solinfbroker.apiautenticacao.model;

import java.util.Set;

public record RegisterDTO(String login, String password, Set<PermissaoModel> role) {
    
}
