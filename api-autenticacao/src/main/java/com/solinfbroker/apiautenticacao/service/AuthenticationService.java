package com.solinfbroker.apiautenticacao.service;

import com.solinfbroker.apiautenticacao.dtos.AuthenticationDTO;
import com.solinfbroker.apiautenticacao.dtos.ClienteModelDTO;
import com.solinfbroker.apiautenticacao.dtos.RegisterDTO;
import com.solinfbroker.apiautenticacao.exception.ApiRequestException;
import com.solinfbroker.apiautenticacao.model.ClienteModel;
import com.solinfbroker.apiautenticacao.model.PermissaoModel;
import com.solinfbroker.apiautenticacao.model.enumTipoPessoa;
import com.solinfbroker.apiautenticacao.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.chrono.ChronoLocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {
    @Autowired
    TokenService tokenService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ValidacoesCliente validacoesCliente;

    @Autowired
    ClienteRepository clienteRepository;
    public String gerarAutenticacao(AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((ClienteModel)auth.getPrincipal());
    }

    public ClienteModelDTO registrarCliente (@Valid RegisterDTO cliente){

        if(this.clienteRepository.findByEmail(cliente.email()) != null){
            throw new ApiRequestException("Já existe um cliente cadastrado com este e-mail.");
        }

        if(cliente.tipo().equals(enumTipoPessoa.PF)){
            if(!validacoesCliente.isValidCPF(cliente.pessoaFisica().iterator().next().getCpf())){

                throw new ApiRequestException("O Cpf do cliente é inválido");
            }else{
                if(!validacoesCliente.validacaoIdade(cliente.pessoaFisica().iterator().next().getDataNascimento())){
                    throw new ApiRequestException("O Cliente deve possuir no minimo 18 anos de idade.");
                }
            }
        }else{
            if(!validacoesCliente.isValidCNPJ(cliente.pessoaJuridica().iterator().next().getCnpj())){
                throw new ApiRequestException("O Cnpj do cliente é inválido");
            }
        }

        Set<PermissaoModel> permissaoModels = new HashSet<>();
        permissaoModels.add(new PermissaoModel(2L,"ROLE_USER"));

        String encryptPassword = new BCryptPasswordEncoder().encode(cliente.senha());

        ClienteModel newUsuarioModel = new ClienteModel(
                cliente.email(),
                encryptPassword,
                permissaoModels,
                cliente.tipo(),
                cliente.nomeUsuario(),
                cliente.pessoaFisica(),
                cliente.pessoaJuridica());
        newUsuarioModel = this.clienteRepository.save(newUsuarioModel);

        return new ClienteModelDTO(
                newUsuarioModel.getId(),
                newUsuarioModel.getEmail(),
                newUsuarioModel.getNomeUsuario(),
                newUsuarioModel.getSaldo(),
                newUsuarioModel.getPessoaFisica(),
                newUsuarioModel.getPessoaJuridica());
    }

    public Boolean validarToken(String token){
        return tokenService.validateToken(token).isEmpty();
    }
}
