package com.springBoot.JPA.webService.resources;

import com.springBoot.JPA.webService.entities.Product;
import com.springBoot.JPA.webService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// CLASSE CONTROLADOR REST FAZ CONEXÃO COM A APPLICAÇÃO BACK END PARA FRONT //
@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    // PARA RETORNAR RESPOSTAS DE REQUISÃO TIPO WEB = REPSPONSE ENTITY //
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
