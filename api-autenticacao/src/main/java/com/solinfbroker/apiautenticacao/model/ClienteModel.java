package com.solinfbroker.apiautenticacao.model;

import java.util.*;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
public class ClienteModel implements UserDetails{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private String nomeUsuario;

    private String senha;

    private String email;

    private double saldo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private Set<PessoaFisica> pessoaFisica = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private Set<PessoaJuridica> pessoaJuridica = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cliente_permissao", joinColumns =  @JoinColumn(name="id_cliente"), inverseJoinColumns = @JoinColumn(name="id_permissao"))
    private Set<PermissaoModel> permissoes;

    public ClienteModel(String email, String senha, Set<PermissaoModel> role, String tipo, String nomeUsuario, Set<PessoaFisica> pessoaFisica, Set<PessoaJuridica> pessoaJuridica){
        this.email = email;
        this.tipo = tipo;
        this.senha = senha;
        this.permissoes = role;
        this.nomeUsuario = nomeUsuario;
        for(PessoaFisica pf : pessoaFisica){
            PessoaFisica pessoaFisica1 = new PessoaFisica();
            pessoaFisica1.setCpf(pf.getCpf());
            pessoaFisica1.setNome(pf.getNome());
            pessoaFisica1.setDataNascimento(pf.getDataNascimento());
            this.pessoaFisica.add(pessoaFisica1);
        }

        for(PessoaJuridica pj : pessoaJuridica){
            PessoaJuridica pessoaJuridica1 = new PessoaJuridica();
            pessoaJuridica1.setCnpj(pj.getCnpj());
            pessoaJuridica1.setNomeFantasia(pj.getNomeFantasia());
            pessoaJuridica1.setRazaoSocial(pj.getRazaoSocial());
            this.pessoaJuridica.add((pessoaJuridica1));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList autorities = new ArrayList<>();
        for (PermissaoModel perm: permissoes){
            autorities.add(new SimpleGrantedAuthority(perm.getPermissao()));
        }
        return autorities;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
