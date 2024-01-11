package com.solinfbroker.apiautenticacao.model;


import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "pessoafisica")
public class PessoaFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "^\\d{11}$", message = "O campo 'cpf' deve conter exatamente 11 dígitos")
    private String cpf;

    @NotNull
    private String nome;

    @Column(name = "nascimento")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataNascimento;

}
