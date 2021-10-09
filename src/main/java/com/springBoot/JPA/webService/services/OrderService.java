package com.springBoot.JPA.webService.services;

import com.springBoot.JPA.webService.entities.Order;
import com.springBoot.JPA.webService.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// PARA QUE O @AUTOWIRED RESOLVA AS QUESTÕES DE DEPENDENCIA QUANDO ESSA CLASSE É INSTANCIADA, USA-SE @Service(de acordo com a finalidade da clasee) PARA REGISTRA-LA NO FRAMEWORK //
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // PARA RETORNAR TODOS OS USUÁRIOS //
    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        Optional<Order> obj = orderRepository.findById(id);
        return obj.get();
    }
}
