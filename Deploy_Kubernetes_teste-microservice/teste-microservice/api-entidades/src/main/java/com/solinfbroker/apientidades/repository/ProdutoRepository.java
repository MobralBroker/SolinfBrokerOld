package com.solinfbroker.apientidades.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solinfbroker.apientidades.model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    
}
