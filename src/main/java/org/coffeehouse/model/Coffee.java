package org.coffeehouse.model;

import java.util.Collection;

public class Coffee extends CoffeeBase<Long> {

    private Double price;
    private String customerName;
    private Collection<Ingredients> extraIngredientsList;
    private Collection<Order> orderList;

    public Coffee() {
    }

    public Coffee(Long id,
                  CoffeeType coffeeType,
                  WhereToDrink whereToDrink,
                  String costumerName,
                  Collection<Ingredients> extraIngredientsList,
                  Collection<Order> orderList) {

        this.customerName = costumerName;
        this.customerName = costumerName;
        this.extraIngredientsList = extraIngredientsList;
    }

    public Double getPrice() {
        return price; //TODO some logic here
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Collection<Ingredients> getExtraIngredientsList() {
        return extraIngredientsList;
    }

    public void setExtraIngredientsList(Collection<Ingredients> extraIngredientsList) {
        this.extraIngredientsList = extraIngredientsList;
    }

    public Collection<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(Collection<Order> orderList) {
        this.orderList = orderList;
    }
}
