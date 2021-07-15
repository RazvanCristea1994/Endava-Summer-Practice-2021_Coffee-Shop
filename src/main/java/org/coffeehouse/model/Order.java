package org.coffeehouse.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order extends AbstractEntity<Long> {

    private Double totalRevenue;
    private LocalDateTime orderDateTime;
    private List<Coffee> orderedCoffeeList = new ArrayList<>();
    private WhereToDrink whereToDrink;
    private Long coffeeId = 0L;

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

    public void setId(Long orderId) {
        super.id = orderId;
    }

    public Double getTotalRevenue() {

        return getOrderedCoffeeList()
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

    public List<Coffee> getOrderedCoffeeList() {
        return orderedCoffeeList;
    }

    public void addCoffeeToOrder(Coffee coffee) {

        setCoffeeIdEqualToIndex(coffee);
        this.orderedCoffeeList.add(coffee);
    }

    private void setCoffeeIdEqualToIndex(Coffee coffee) {

        Integer size = Integer.valueOf(this.orderedCoffeeList.size());
        coffee.setId(size--);
    }

    public WhereToDrink getWhereToDrink() {
        return whereToDrink;
    }

    public void setWhereToDrink(WhereToDrink whereToDrink) {
        this.whereToDrink = whereToDrink;
    }
}
