package com.springBoot.JPA.webService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

// SERIALIZABLE USAGE FOR TRAFFIC IN ETHERNET AND TO RECORD OBJ INTO ARCHIVES - ELE "COMPACTA" SALVA E "DESCOMPACTA" //
// TABLE SER PARA MUNDAR O NOME DA TABELA NO BANCO DE DADOS SQL PARA EVITAR CONFLITO ENTRE NOMES //
@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    // O @ID SERVE PARA DEFINIR QUE O MEU LONG ID SERÁ A CHAVE PRIMÁRIA DE BUSCA NO MEU BANCO DE DADOS E O @GNERATEDVALUE DIZ QUE O ID //
    // É CRIADO AUTOMATICAMENTE PELO BANCO DE DADOS //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant moment;

    // PARA FAZER ASSOCIAÇÃO UM PARA UM //
    // JsonIgnore FAZ COM QUE A ASSOCIAÇÃO NÃO CAIA EM UM LOOP QUANDO EXECUTADA EX: PEDIDO - CLIENTE - CLIENTE -PEDIDO E POR AI VAI //
    @JsonIgnore
    @OneToOne
    @MapsId
    private Order order;

    public Payment(){
    }

    public Payment(Long id, Instant moment, Order order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
