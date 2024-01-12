package com.solinfbroker.apiautenticacao.controller;

import com.solinfbroker.apiautenticacao.dtos.AuthResponseDTO;
import com.solinfbroker.apiautenticacao.dtos.AuthenticationDTO;
import com.solinfbroker.apiautenticacao.dtos.RegisterDTO;
import com.solinfbroker.apiautenticacao.repository.ClienteRepository;
import com.solinfbroker.apiautenticacao.service.AuthenticationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    ClienteRepository clienteRepository;


    @PostMapping("/login") // Método responsável por efetuar a autenticação e gerar o token de acesso ao cliente
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        String token = authenticationService.gerarAutenticacao(data);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) throws Exception {

        return ResponseEntity.ok().body(authenticationService.registrarCliente(data));
    }

    @GetMapping("/validar")
    public ResponseEntity<String> validarToken (@RequestParam("token") String token) {
        if(authenticationService.validarToken(token)){
            return new ResponseEntity<>("Token Não Autorizado",HttpStatus.UNAUTHORIZED); 
        };
        return new ResponseEntity<>("Token Autorizado",HttpStatus.ACCEPTED); 

    }
    
    @GetMapping("/usuario/{login}")
    public ResponseEntity getUsuario(@PathVariable String login) {
        return ResponseEntity.ok().body(clienteRepository.findByEmail(login));
    }
    
    
}
