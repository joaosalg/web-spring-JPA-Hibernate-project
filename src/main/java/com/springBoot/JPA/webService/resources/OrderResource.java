package com.springBoot.JPA.webService.resources;

import com.springBoot.JPA.webService.entities.Order;
import com.springBoot.JPA.webService.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// CLASSE CONTROLADOR REST - FAZ CONEXÃO COM A APPLICAÇÃO BACK END PARA FRONT //
@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    // PARA RETORNAR RESPOSTAS DE REQUISÃO TIPO WEB = REPSPONSE ENTITY //
    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
