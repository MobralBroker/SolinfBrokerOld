package com.solinfbroker.apiautenticacao.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "permissoes")
public class PermissaoModel {
    
    @Id
    @Column(name = "id")
    private Long id;
    
    @Column(name = "permissao")
    private String permissao;
}
