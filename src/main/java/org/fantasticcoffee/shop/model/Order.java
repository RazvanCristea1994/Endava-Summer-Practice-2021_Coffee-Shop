package org.fantasticcoffee.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String customerName;

    @Column
    private LocalDateTime orderDateTime;

    @OneToMany(mappedBy = "order")
    private List<Coffee> coffeeList;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @Column(nullable = false)
    private Double price;

    private Order(Builder builder) {
        this.id = builder.id;
        this.customerName = builder.customerName;
        this.orderDateTime = builder.orderDateTime;
        this.coffeeList = builder.coffeeList;
        this.orderType = builder.orderType;
        this.card = builder.card;
        this.price = builder.price;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Builder {

        private Integer id;
        private String customerName;
        private LocalDateTime orderDateTime;
        private List<Coffee> coffeeList = new ArrayList<>();
        private OrderType orderType;
        private Card card;
        private Double price;

        public void addCoffee(Coffee coffee) {
            this.coffeeList.add(coffee);
        }

        public Order build() {
            return new Order(this);
        }
    }
}