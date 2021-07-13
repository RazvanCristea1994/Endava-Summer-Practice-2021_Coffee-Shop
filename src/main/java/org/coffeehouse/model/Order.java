package org.coffeehouse.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class Order {

    private Long id;
    private Double totalCost;
    private LocalDateTime orderDateTime;
    private Collection<Coffee> orderedCoffeeList;

    public Order() {
    }

    public Order(Long id, LocalDateTime orderDateTime, Collection<Coffee> orderedCoffeeList) {
        this.id = id;
        this.orderDateTime = orderDateTime;
        this.orderedCoffeeList = orderedCoffeeList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
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
}
