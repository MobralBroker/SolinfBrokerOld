package com.solinfbroker.apiautenticacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pessoajuridica")
public class PessoaJuridica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "^\\d{14}$", message = "O campo 'cnpj' deve conter exatamente 14 dígitos")
    private String cnpj;

    @NotNull
    @Column(name = "razao_social")
    private String razaoSocial;

    @NotNull
    @Column(name = "nome_fantasia")
    private String nomeFantasia;

}
