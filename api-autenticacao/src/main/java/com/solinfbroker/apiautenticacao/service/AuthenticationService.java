package com.solinfbroker.apiautenticacao.service;

import com.solinfbroker.apiautenticacao.dtos.AuthenticationDTO;
import com.solinfbroker.apiautenticacao.dtos.RegisterDTO;
import com.solinfbroker.apiautenticacao.exception.ApiRequestException;
import com.solinfbroker.apiautenticacao.model.ClienteModel;
import com.solinfbroker.apiautenticacao.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    TokenService tokenService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ClienteRepository clienteRepository;
    public String gerarAutenticacao(AuthenticationDTO data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((ClienteModel)auth.getPrincipal());
    }

    public ClienteModel registrarCliente (RegisterDTO cliente){

        if(this.clienteRepository.findByEmail(cliente.email()) != null){
            throw new ApiRequestException("Já existe um cliente cadastrado com este e-mail.");
        }

        String encryptPassword = new BCryptPasswordEncoder().encode(cliente.senha());

        ClienteModel newUsuarioModel = new ClienteModel(
                cliente.email(),
                encryptPassword,
                cliente.role(), cliente.tipo(), cliente.nomeUsuario(), cliente.pessoaFisica(), cliente.pessoaJuridica());
        return this.clienteRepository.save(newUsuarioModel);
    }

    public Boolean validarToken(String token){
        return tokenService.validateToken(token).isEmpty();
    }
}
