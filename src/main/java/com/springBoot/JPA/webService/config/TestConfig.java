package com.springBoot.JPA.webService.config;

import com.springBoot.JPA.webService.entities.*;
import com.springBoot.JPA.webService.entities.enums.OrderStatus;
import com.springBoot.JPA.webService.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

// CLASSE DE CONFIGURAÇÃO ESPECIFICA PARA O PERFIL DE TEST //
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    // AUTOWIRE DO SPRING RESOLVE A DEPENDECIA E ASSOCIAR UMA INSTANCIA DE USERREPOSITORY//
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    // PARA FAZER COM QUE ESSA CLASSE EXECUTE QUANDO A APLICAÇÃO FOR INICIADA //
    // O HORÁRIO DOS OBJETOS ORDER ESTÃO FORMATADOS EM PADRAO ISO 8601 QUE POSSIBILITA VARIAS POSSIBILIDADES DE HORÁRIO Z NO FIM = UTC //
    // LAZYLOADING = SE VC PEDIR PARA O POSTMAN CARREGAR O CLIENT ELE CARREGARÁ (UM PARA MUITOS) SOMENTE OS CLIENTES JÁ OS PEDIDOS(ORDERS) VIRÃO CARREGADOS COM O CLIENT -jsonIgnore que muda a perspectiva //
    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");


        // PARA CRIAR E SALVAR EM UMA LISTA - QUEM É RESPONSAVEL POR SALVAR E ETC É O USERREPOSITORY POR ISSO FOI INSTANCIADO//
        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        // PARA ASSOCIAR A CATEGORIA COM O PRODUTO NO BANCO H2 //
        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat1);
        p4.getCategories().add(cat1);
        p5.getCategories().add(cat2);
        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
    }
}
