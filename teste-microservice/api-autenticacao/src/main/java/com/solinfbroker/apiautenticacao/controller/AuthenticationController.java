package com.solinfbroker.apiautenticacao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solinfbroker.apiautenticacao.model.AuthResponse;
import com.solinfbroker.apiautenticacao.model.AuthenticationDTO;
import com.solinfbroker.apiautenticacao.model.RegisterDTO;
import com.solinfbroker.apiautenticacao.model.UsuarioModel;
import com.solinfbroker.apiautenticacao.repository.UsuarioRepository;
import com.solinfbroker.apiautenticacao.service.TokenService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
    UsuarioRepository usuarioRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UsuarioModel)auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(this.usuarioRepository.findByLogin(data.login()) != null){
            return ResponseEntity.badRequest().build();
        } 

        String encryptPassaword = new BCryptPasswordEncoder().encode(data.password());

        UsuarioModel newUsuarioModel = new UsuarioModel(data.login(),encryptPassaword,data.role());
        this.usuarioRepository.save(newUsuarioModel);
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
        return ResponseEntity.ok().body(usuarioRepository.findByLogin(login));
    }
    
    
}
