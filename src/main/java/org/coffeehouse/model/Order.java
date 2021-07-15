package org.coffeehouse.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class Order extends AbstractEntity<Long> {

    private Double totalRevenue;
    private LocalDateTime orderDateTime;
    private Collection<Coffee> orderedCoffeeList = new ArrayList<>();
    private WhereToDrink whereToDrink;

    public Order() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalRevenue() {

        return getOrderedCoffeeList()
                .stream()
                .mapToDouble(Coffee::getPrice)
                .sum();
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public Collection<Coffee> getOrderedCoffeeList() {
        return orderedCoffeeList;
    }

    public void setOrderedCoffeeList(Collection<Coffee> orderedCoffeeList) {
        this.orderedCoffeeList = orderedCoffeeList;
    }

    public void addCoffeeToOrder(Coffee coffee) {
        this.orderedCoffeeList.add(coffee);
    }

    public WhereToDrink getWhereToDrink() {
        return whereToDrink;
    }

    public void setWhereToDrink(WhereToDrink whereToDrink) {
        this.whereToDrink = whereToDrink;
    }
}
