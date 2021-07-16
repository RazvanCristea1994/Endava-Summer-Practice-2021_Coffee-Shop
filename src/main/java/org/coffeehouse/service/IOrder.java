package org.coffeehouse.service;

import org.coffeehouse.model.Order;

import java.util.List;

public interface IOrder {

    Order placeOrder(Order IOrder);

    List<Order> findAll();

    Order findOrder(Long id);

    Order update(Order order);

    Double getTotalProfit();

    Double getTotalProfitForToday();

    Order deleteOrder(Long id);
}
