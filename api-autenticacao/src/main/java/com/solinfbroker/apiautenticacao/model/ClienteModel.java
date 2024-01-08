package com.solinfbroker.apiautenticacao.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

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
    @Column(name = "id_cliente")
    private Long id;

    private String tipo;

    @Column(name = "usuario")
    private String nomeUsuario;

    private String senha;

    private String email;

    private double saldo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private Set<PessoaFisica> pessoaFisica;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private Set<PessoaJuridica> pessoaJuridica;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cliente_permissao", joinColumns =  @JoinColumn(name="id_cliente"), inverseJoinColumns = @JoinColumn(name="id_permissao"))
    private Set<PermissaoModel> permissoes;

    public ClienteModel(String email, String senha, Set<PermissaoModel> role, String tipo, String nomeUsuario, Set<PessoaFisica> pessoaFisica){
        this.email = email;
        this.tipo = tipo;
        this.senha = senha;
        this.permissoes = role;
        this.nomeUsuario = nomeUsuario;
        for(PessoaFisica pf : pessoaFisica){
            this.pessoaFisica.add(pf);
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
