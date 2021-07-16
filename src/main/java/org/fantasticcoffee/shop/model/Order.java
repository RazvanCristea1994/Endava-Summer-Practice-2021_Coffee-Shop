package org.fantasticcoffee.shop.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order extends AbstractEntity {

    private LocalDateTime orderDateTime;
    private List<Coffee> orderCoffeeList = new ArrayList<>();
    private WhereToDrink whereToDrink;

    public Order() {
    }

    public Order copyOrderObject(Order order) {
        this.setId(order.getId());
        order.getOrderCoffeeList().forEach(this.orderCoffeeList::add);
        this.setOrderDateTime(order.getOrderDateTime());
        this.setWhereToDrink(order.getWhereToDrink());

        return this;
    }

    public enum WhereToDrink {
        PICK_UP("pick-up"), TO_GO("to-go");

        private String name;

        WhereToDrink(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public Double getTotalRevenue() {

        return getOrderCoffeeList()
                .stream()
                .mapToDouble(Coffee::getPrice)
                .sum();
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public List<Coffee> getOrderCoffeeList() {
        return orderCoffeeList;
    }

    public void addCoffeeToOrder(Coffee coffee) {
        this.orderCoffeeList.add(coffee);
    }

    public WhereToDrink getWhereToDrink() {
        return whereToDrink;
    }

    public void setWhereToDrink(WhereToDrink whereToDrink) {
        this.whereToDrink = whereToDrink;
    }

    public void setOrderCoffeeList(List<Coffee> orderCoffeeList) {
        this.orderCoffeeList = orderCoffeeList;
    }
}
