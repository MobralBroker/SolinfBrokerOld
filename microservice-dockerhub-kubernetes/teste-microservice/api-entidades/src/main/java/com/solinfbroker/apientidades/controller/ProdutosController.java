package com.solinfbroker.apientidades.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solinfbroker.apientidades.dto.ProdutoDto;
import com.solinfbroker.apientidades.model.ProdutoModel;
import com.solinfbroker.apientidades.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("produtos")
public class ProdutosController {
    
    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<ProdutoModel> saveProduto(@RequestBody @Valid ProdutoDto produtoDTO) {
        var produtoModel= new ProdutoModel();
        BeanUtils.copyProperties(produtoDTO, produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
    }
    
    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarProdutos() {
        return new ResponseEntity<>(produtoRepository.findAll(),HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Optional<ProdutoModel>> getProduto(@PathVariable Long id) {
        return new ResponseEntity<>(produtoRepository.findById(id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProdutoModel> deleteProduto(@PathVariable Long id){   
        produtoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
    
}