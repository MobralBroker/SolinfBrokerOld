package com.solinfbroker.apiautenticacao.model;


import jakarta.persistence.*;
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
    @Column(name = "id_pessoafisica")
    private Long id;

    private String cpf;

    private String nome;

    @Column(name = "nascimento")
    private LocalDate dataNascimento;


}
