package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Order {

    private Integer id;
    private String customerName;
    private LocalDateTime orderDateTime;
    private List<Coffee> coffeeList;
    private WhereToDrink whereToDrink;
    private Card card;
    private Double price;

    private Order(Builder builder) {
        this.id = builder.id;
        this.customerName = builder.customerName;
        this.orderDateTime = builder.orderDateTime;
        this.coffeeList = builder.coffeeList;
        this.whereToDrink = builder.whereToDrink;
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
        private WhereToDrink whereToDrink;
        private Card card;
        private Double price;

        public void addCoffee(Coffee coffee) {
            this.coffeeList.add(coffee);
        }

        public Order build() {
            return new Order(this);
        }
    }

    public Order duplicate() {

        Order.Builder builder = new Order.Builder();
        builder.setId(this.getId());
        builder.setCustomerName(this.getCustomerName());
        builder.setOrderDateTime(this.orderDateTime);
        builder.setCoffeeList(this.coffeeList);
        builder.setWhereToDrink(this.whereToDrink);
        builder.setCard(this.card);
        builder.setPrice(this.price);
        return builder.build();
    }
}