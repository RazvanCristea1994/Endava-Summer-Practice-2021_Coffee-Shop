package org.fantasticcoffee.shop.service;

import org.fantasticcoffee.shop.model.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(Order IOrder);

    List<Order> findAll();

    Order findOrder(Integer id);

    Order update(Integer id, Order order);

    Double getTotalProfit();

    Double getTotalOrderPrice(Order order);

    Double getTotalProfitForToday();

    void deleteOrder(Integer id);
}