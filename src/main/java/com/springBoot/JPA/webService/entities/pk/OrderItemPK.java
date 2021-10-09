package com.springBoot.JPA.webService.entities.pk;

import com.springBoot.JPA.webService.entities.Order;
import com.springBoot.JPA.webService.entities.Product;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

// CLASSE AUXILIAR QUE REPRESENTA O PAR (PRODUCT E PEDIDO) QUE IRÁ ESTABELECER A CHAVE PRIMÁRIA DO ORDERITEM E AS ASSOCIAÇÕES ENTRA PRODUCTO E PEDIDO //
// SERIALIZABLE USAGE FOR TRAFFIC IN ETHERNET AND TO RECORD OBJ INTO ARCHIVES //
// POR SER UMA CHAVE AUXILIAR COMPOSTA = @EMBEDDABLE //
@Embeddable
public class OrderItemPK implements Serializable {

    private static final long serialVersionUID = 1L;

    // PARA FAZER A ASSOCIAÇÃO MUITOS PARA UM //
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // PARA FAZER A ASSOCIAÇÃO MUITOS PARA UM //
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
