package com.springBoot.JPA.webService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// SERIALIZABLE USAGE FOR TRAFFIC IN ETHERNET AND TO RECORD OBJ INTO ARCHIVES //
// TABLE SER PARA MUNDAR O NOME DA TABELA NO BANCO DE DADOS SQL PARA EVITAR CONFLITO ENTRE NOMES //
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    // O @ID SERVE PARA DEFINIR QUE O MEU LONG ID SERÁ A CHAVE PRIMÁRIA DE BUSCA NO MEU BANCO DE DADOS E O @GNERATEDVALUE DIZ QUE O ID //
    // É CRIADO AUTOMATICAMENTE PELO BANCO DE DADOS //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    // PARA ASSOCIAR UM-PARA MUITOS NO JPA //
    // JsonIgnore FAZ COM QUE A ASSOCIAÇÃO NÃO CAIA EM UM LOOP QUANDO EXECUTADA EX: PEDIDO - CLIENTE - CLIENTE -PEDIDO E POR AI VAI //
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    public User(){
    }

    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, password);
    }
}
