package com.solinfbroker.apiautenticacao.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * Classe responsável pela modelagem de dados referente as permissões,
 * utilizada para mapear a tabela 'permissões' do banco de dados.
 *
 * Anotações utilizadas:
 * @Entity: indica que essa classe é uma entidade (uma classe que o JPA gerencia).
 * @Data: anotação do Lombok para gerar automaticamente getters, setters, toString, equal e hashCode.
 * @EqualsAndHashCode: anotação do Lombok para avançar nas gerações do equals e hashCode.
 * @Table: anotação usada para indicar qual é a tabela que esse modelo representa, neste caso, a tabela 'permissoes'.
 *
 * Atributos:
 * id: identificador único da permissão, corresponde à coluna 'id' na tabela 'permissoes'.
 * permissao: representa a descrição da permissão, corresponde à coluna 'permissao' na tabela 'permissoes'.
 */
@Entity
@Data
@EqualsAndHashCode
@Table(name = "permissoes")
public class PermissaoModel {
    
    @Id
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "permissao")
    private String permissao; // Descrição da permissão.

    public PermissaoModel(Long id, String permissao) {
        this.id = id;
        this.permissao = permissao;
    }

    public PermissaoModel(){
    }
}
