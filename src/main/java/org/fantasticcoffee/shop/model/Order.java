package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.fantasticcoffee.shop.model.coffee.CoffeeWithStandardRecipeBase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Order {

    private Integer id;
    private LocalDateTime orderDateTime;
    private List<CustomCoffee> customCoffeeList;
    private List<CoffeeWithStandardRecipeBase> coffeeWithStandardRecipeBase;
    private WhereToDrink whereToDrink;
    private Card card;
    private Double price;

    private Order(Builder builder) {
        this.id = builder.id;
        this.orderDateTime = builder.orderDateTime;
        this.customCoffeeList = builder.customCoffeeList;
        this.coffeeWithStandardRecipeBase = builder.coffeeWithStandardRecipeBase;
        this.whereToDrink = builder.whereToDrink;
        this.card = builder.card;
        this.price = builder.price;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Builder {

        private Integer id;
        private LocalDateTime orderDateTime;
        private List<CustomCoffee> customCoffeeList = new ArrayList<>();
        private List<CoffeeWithStandardRecipeBase> coffeeWithStandardRecipeBase = new ArrayList<>();
        private WhereToDrink whereToDrink;
        private Card card;
        private Double price;

        public void addCustomCoffee(CustomCoffee coffee) {
            this.customCoffeeList.add(coffee);
        }

        public void addCustomizableStandardCoffee(CoffeeWithStandardRecipeBase coffee) {
            this.coffeeWithStandardRecipeBase.add(coffee);
        }

        public Order build() {
            return new Order(this);
        }
    }

    public Order duplicate() {

        Order.Builder builder = new Order.Builder();
        builder.setId(this.getId());
        builder.setOrderDateTime(this.orderDateTime);
        builder.setCustomCoffeeList(this.customCoffeeList);
        builder.setCoffeeWithStandardRecipeBase(this.coffeeWithStandardRecipeBase);
        builder.setWhereToDrink(this.whereToDrink);
        builder.setCard(this.card);
        builder.setPrice(this.price);
        return builder.build();
    }
}