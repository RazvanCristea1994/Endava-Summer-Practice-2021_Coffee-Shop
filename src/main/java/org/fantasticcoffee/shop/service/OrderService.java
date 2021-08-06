package org.fantasticcoffee.shop.service;

import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Order placeOrder(Order order);

    List<Order> findAll();

    Order findOrder(Integer id);

    Order update(Integer id, Order order);

    Double getTotalProfit();

    Double getTotalOrderPrice(Order order);

    Double getTotalProfitForToday();

    void deleteOrder(Integer id);

    Map<IngredientInStock, Integer> getAllIngredientsForOrder(Order order);
}