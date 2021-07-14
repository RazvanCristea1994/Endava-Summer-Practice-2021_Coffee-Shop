package org.coffeehouse.model;

import java.time.LocalDateTime;
import java.util.Collection;

public class Order extends AbstractEntity<Long> {

    private Double totalRevenue;
    private Double totalCost;
    private LocalDateTime orderDateTime;
    private Collection<Coffee> orderedCoffeeList;

    public Order(Collection<Coffee> orderedCoffeeList) {
        this.orderedCoffeeList = orderedCoffeeList;
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
