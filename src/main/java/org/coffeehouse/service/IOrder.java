package org.coffeehouse.service;

import org.coffeehouse.model.Order;

import java.util.Collection;

public interface IOrder {

    Order placeOrder(Order IOrder);

    Collection<Order> findAll();

    Double getTotalProfit();

    Double getTotalProfitForToday();

    Order cancelOrder(Long id);
    //ToDo
}
