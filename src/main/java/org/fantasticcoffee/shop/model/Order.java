package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;

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
    private List<CustomizableStandardCoffee> customizableStandardCoffee;
    private WhereToDrink whereToDrink;

    public Order(Builder builder) {
        this.id = builder.id;
        this.orderDateTime = builder.orderDateTime;
        this.customCoffeeList = builder.customCoffeeList;
        this.customizableStandardCoffee = builder.customizableStandardCoffee;
        this.whereToDrink = builder.whereToDrink;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Builder {

        private Integer id;
        private LocalDateTime orderDateTime;
        private List<CustomCoffee> customCoffeeList = new ArrayList<>();
        private List<CustomizableStandardCoffee> customizableStandardCoffee = new ArrayList<>();
        private WhereToDrink whereToDrink;

        public void addCustomCoffee(CustomCoffee coffee) {
            this.customCoffeeList.add(coffee);
        }

        public void addCustomizableStandardCoffee(CustomizableStandardCoffee coffee) {
            this.customizableStandardCoffee.add(coffee);
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
        builder.setCustomizableStandardCoffee(this.customizableStandardCoffee);
        builder.setWhereToDrink(this.whereToDrink);
        return builder.build();
    }
}