package org.coffeehouse.model;

import java.time.LocalDateTime;
import java.util.Collection;

public class Order extends AbstractEntity<Long> {

    private Double totalRevenue;
    private LocalDateTime orderDateTime;
    private Collection<Coffee> orderedCoffeeList;
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
        return totalRevenue;
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

    public WhereToDrink getWhereToDrink() {
        return whereToDrink;
    }

    public void setWhereToDrink(WhereToDrink whereToDrink) {
        this.whereToDrink = whereToDrink;
    }
}
