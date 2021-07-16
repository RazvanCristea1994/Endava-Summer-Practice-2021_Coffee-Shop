package org.fantasticcoffee.shop.service;

import org.fantasticcoffee.shop.model.Order;

import java.util.List;

public interface IOrder {

    Order placeOrder(Order IOrder);

    List<Order> findAll();

    Order findOrder(Integer id);

    Order update(Order order);

    Double getTotalProfit();

    Double getTotalProfitForToday();

    Order deleteOrder(Integer id);
}
