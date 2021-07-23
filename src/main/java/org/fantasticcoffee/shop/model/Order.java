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
    private LocalDateTime orderDateTime;
    private List<Coffee> coffeeList;
    private WhereToDrink whereToDrink;

    public Order(LocalDateTime orderDateTime, List<Coffee> coffeeList, WhereToDrink whereToDrink) {
        this.orderDateTime = orderDateTime;
        this.coffeeList = coffeeList;
        this.whereToDrink = whereToDrink;
    }

    public Order(Integer id, LocalDateTime orderDateTime, List<Coffee> coffeeList, WhereToDrink whereToDrink) {
        this.id = id;
        this.orderDateTime = orderDateTime;
        this.coffeeList = coffeeList;
        this.whereToDrink = whereToDrink;
    }

    public Order duplicate() {

        return new Order(this.getId(),
                this.getOrderDateTime(),
                new ArrayList<>(this.getCoffeeList()),
                this.getWhereToDrink());
    }
}