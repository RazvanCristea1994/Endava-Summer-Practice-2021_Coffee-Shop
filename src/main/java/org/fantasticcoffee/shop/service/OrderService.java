package org.fantasticcoffee.shop.service;

import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;

import java.util.List;

public interface OrderService {

    Order placeOrder(Order order, List<IngredientOnRecipe> allIngredientsInOrder);

    List<Order> findAll();

    Order findOrder(Integer id);

    Order update(Integer id, Order order);

    Double getTotalProfit();

    Double getTotalOrderPrice(Order order);

    Double getTotalProfitForToday();

    void deleteOrder(Integer id);

    List<IngredientOnRecipe> getAllIngredientsForOrder(Order order);
}