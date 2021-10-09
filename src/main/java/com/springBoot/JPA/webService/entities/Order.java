package com.springBoot.JPA.webService.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springBoot.JPA.webService.entities.enums.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// SERIALIZABLE USAGE FOR TRAFFIC IN ETHERNET AND TO RECORD OBJ INTO ARCHIVES //
// TABLE SER PARA MUNDAR O NOME DA TABELA NO BANCO DE DADOS SQL PARA EVITAR CONFLITO ENTRE NOMES //
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    // O @ID SERVE PARA DEFINIR QUE O MEU LONG ID SERÁ A CHAVE PRIMÁRIA DE BUSCA NO MEU BANCO DE DADOS E O @GNERATEDVALUE DIZ QUE O ID //
    // É CRIADO AUTOMATICAMENTE PELO BANCO DE DADOS //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // PARA QUE O INSTANT SEJA FORMATADO NO JSON //
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    // MAÇETE PARA O ENUM //
    private Integer orderStatus;

    // PARA TRANSFORMAR ESSA ASSOCIAÇÃO MUITOS-PARA UM NO JPA//
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    // PARA ASSOCIAR UM PRA MUITOS - PARA RETORNAR OS ORDER ITENS ASSOCIADOS AO PEDIDO //
    // id.order pq é o ID NO ORDERITEM QUE TEM O PEDIDO //
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    public Order() {
    }

    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
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

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public Set<OrderItem> getItems(){
        return items;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
