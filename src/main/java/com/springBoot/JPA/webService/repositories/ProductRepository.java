package com.springBoot.JPA.webService.repositories;

import com.springBoot.JPA.webService.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// N PRECISA REGISTRAR NO FRAMEWORK POR QUE O JPAREPOSITORY JÁ ESTA REGISTRADO //
// A CLASSE REPOSITORY SERVE PARA SALVAR NO BANCO DE DADOS DE TESTE H2 //
public interface ProductRepository extends JpaRepository<Product, Long> {
}
