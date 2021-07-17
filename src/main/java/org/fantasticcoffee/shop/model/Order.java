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

    public Order(LocalDateTime orderDateTime, List<Coffee> orderCoffeeList, WhereToDrink whereToDrink) {
        this.orderDateTime = orderDateTime;
        this.orderCoffeeList = orderCoffeeList;
        this.whereToDrink = whereToDrink;
    }

    public static Order copyOrderObject(Order order) { //ToDo: read about clone() method

        Order copyOrder = new Order();
        copyOrder.setId(order.getId());
        order.getCoffeeList().forEach(copyOrder.orderCoffeeList::add);
        copyOrder.setOrderDateTime(order.getOrderDateTime());
        copyOrder.setWhereToDrink(order.getWhereToDrink());

        return copyOrder;
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

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public List<Coffee> getCoffeeList() {
        return orderCoffeeList;
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
