package com.solinfbroker.apiautenticacao.controller;

import com.solinfbroker.apiautenticacao.model.ClienteModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solinfbroker.apiautenticacao.dtos.AuthResponseDTO;
import com.solinfbroker.apiautenticacao.dtos.AuthenticationDTO;
import com.solinfbroker.apiautenticacao.dtos.RegisterDTO;
import com.solinfbroker.apiautenticacao.repository.ClienteRepository;
import com.solinfbroker.apiautenticacao.service.TokenService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((ClienteModel)auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(this.clienteRepository.findByEmail(data.email()) != null){
            return ResponseEntity.badRequest().build();
        } 

        String encryptPassaword = new BCryptPasswordEncoder().encode(data.senha());

        ClienteModel newUsuarioModel = new ClienteModel(data.email(),encryptPassaword,data.role(), data.tipo(), data.nomeUsuario(), data.pessoaFisica());
        this.clienteRepository.save(newUsuarioModel);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/validar")
    public ResponseEntity<?> validarToken (@RequestParam("token") String token) {
        if(tokenService.validateToken(token).isEmpty()){
            return new ResponseEntity<>("Token Não Autorizado",HttpStatus.UNAUTHORIZED); 
        };
        return new ResponseEntity<>("Token Autorizado",HttpStatus.ACCEPTED); 

    }
    
    
    @GetMapping("/usuario/{login}")
    public ResponseEntity getUsuario(@PathVariable String login) {
        return ResponseEntity.ok().body(clienteRepository.findByEmail(login));
    }
    
    
}
