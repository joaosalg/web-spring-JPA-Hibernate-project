package com.springBoot.JPA.webService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// SERIALIZABLE USAGE FOR TRAFFIC IN ETHERNET AND TO RECORD OBJ INTO ARCHIVES //
// TABLE SER PARA MUNDAR O NOME DA TABELA NO BANCO DE DADOS SQL PARA EVITAR CONFLITO ENTRE NOMES //
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    // O @ID SERVE PARA DEFINIR QUE O MEU LONG ID SERÁ A CHAVE PRIMÁRIA DE BUSCA NO MEU BANCO DE DADOS E O @GNERATEDVALUE DIZ QUE O ID //
    // É CRIADO AUTOMATICAMENTE PELO BANCO DE DADOS //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // PARA PEGAR A REFERENCIA DO MAPEAMENTO MANY TO MANY FEITO EM PRODUCTS //
    // JsonIgnore FAZ COM QUE A ASSOCIAÇÃO NÃO CAIA EM UM LOOP QUANDO EXECUTADA EX: PEDIDO - CLIENTE - CLIENTE -PEDIDO E POR AI VAI //
    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    public Category(){
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
