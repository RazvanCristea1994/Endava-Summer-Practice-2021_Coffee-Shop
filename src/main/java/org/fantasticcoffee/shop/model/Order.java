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
public class Order extends AbstractEntity {

    private LocalDateTime orderDateTime;
    private List<Coffee> coffeeList = new ArrayList<>();
    private WhereToDrink whereToDrink;

    public Order(LocalDateTime orderDateTime, List<Coffee> coffeeList, WhereToDrink whereToDrink) {
        this.orderDateTime = orderDateTime;
        this.coffeeList = coffeeList;
        this.whereToDrink = whereToDrink;
    }

    public static Order copyOrderObject(Order order) { //ToDo: read about clone() method

        Order copyOrder = new Order();
        copyOrder.setId(order.getId());
        order.getCoffeeList().forEach(copyOrder.coffeeList::add);
        copyOrder.setOrderDateTime(order.getOrderDateTime());
        copyOrder.setWhereToDrink(order.getWhereToDrink());

        return copyOrder;
    }

    public enum WhereToDrink {
        PICK_UP("Pick-Up"), TO_GO("To-Go");

        private String name;

        WhereToDrink(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
