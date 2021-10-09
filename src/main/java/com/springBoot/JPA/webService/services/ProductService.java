package com.springBoot.JPA.webService.services;

import com.springBoot.JPA.webService.entities.Product;
import com.springBoot.JPA.webService.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// PARA QUE O @AUTOWIRED RESOLVA AS QUESTÕES DE DEPENDENCIA QUANDO ESSA CLASSE É INSTANCIADA, USA-SE @Service(de acordo com a finalidade da clasee) PARA REGISTRA-LA NO FRAMEWORK //
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // PARA RETORNAR TODOS OS USUÁRIOS //
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> obj = productRepository.findById(id);
        return obj.get();
    }
}
