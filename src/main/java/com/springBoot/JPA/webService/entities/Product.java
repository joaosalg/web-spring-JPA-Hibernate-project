package com.springBoot.JPA.webService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// SERIALIZABLE USAGE FOR TRAFFIC IN ETHERNET AND TO RECORD OBJ INTO ARCHIVES //
// TABLE SER PARA MUNDAR O NOME DA TABELA NO BANCO DE DADOS SQL PARA EVITAR CONFLITO ENTRE NOMES //
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    // O @ID SERVE PARA DEFINIR QUE O MEU LONG ID SERÁ A CHAVE PRIMÁRIA DE BUSCA NO MEU BANCO DE DADOS E O @GNERATEDVALUE DIZ QUE O ID //
    // É CRIADO AUTOMATICAMENTE PELO BANCO DE DADOS //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    // USAR O SET PARA GARANTIR QUE NÃO HAJA UM PRODUTO COM MAIS DE UMA CATEGORIA //
    // NÃO COLOCAR COLEÇÃO NO CONSTRUTOR PQ ELA JÁ ESTÁ INSTANCIADA //
    // PARA FAZER ASSOCIAÇÃO MANYTOMANY, JOINCOLUNS = DEFINE CHAVE ESTRANGEIRA DO PRODUCTO E INVERSE = CHAVE ESTRANGEIRA DA CATEGORIA //
    @ManyToMany
    @JoinTable(name = "tb_product_category",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
