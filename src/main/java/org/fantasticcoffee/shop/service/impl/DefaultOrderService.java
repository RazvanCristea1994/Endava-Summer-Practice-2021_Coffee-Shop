package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.Ingredient;
import org.fantasticcoffee.shop.repository.database.OrderRepository;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.fantasticcoffee.shop.service.IngredientService;
import org.fantasticcoffee.shop.service.OrderService;
import org.fantasticcoffee.shop.validator.CardValidation;
import org.fantasticcoffee.shop.validator.CoffeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

@Service("orderService")
public class DefaultOrderService implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private IngredientService ingredientService;

    @Override
    public Order placeOrder(Order order) {

        CoffeeValidator.coffeeCheck(order);
        Map<Ingredient, Integer> allIngredientsInOrder = ingredientService.checkIngredientsForOrder(order);
        CardValidation.cardNumberValidation(order.getCard().getCardNumber());

        order.setOrderDateTime(LocalDateTime.now());
        order.setPrice(getTotalOrderPrice(order));

        Order result = this.orderRepository.save(order);
        this.coffeeService.save(order);
        this.ingredientService.decrementIngredient(allIngredientsInOrder);

        return result;
    }

    @Override
    public List<Order> findAll() {

        Iterable<Order> orders = this.orderRepository.findAll();
        List<Order> orderList = new ArrayList<>();
        orders.forEach(orderList::add);

        return orderList;
    }

    @Override
    public Order findOrder(Integer id) {

        Optional<Order> result = this.orderRepository.findById(id);
        return result.orElseThrow();
    }

    @Override
    public Order update(Integer id, Order order) {

        if (order == null) {
            throw new IllegalArgumentException("Illegal attempt! Please specify one of the orders you want to update");
        }

        Optional<Order> oldOrder = this.orderRepository.findById(id);
        if (oldOrder.isEmpty()) {
            throw new NoSuchElementException("The order to change was not found");
        }

        order.setOrderDateTime(LocalDateTime.now());
        order.setId(id);
        return this.orderRepository.save(order);
    }

    @Override
    public Double getTotalProfit() {

        List<Order> orderList = findAll();

        double revenueCustomCoffeeTotal = orderList.stream()
                .mapToDouble(order -> order.getCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeePrice(coffee)).sum()).sum();

        double costCustomCoffeeTotal = orderList.stream()
                .mapToDouble(order -> order.getCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeeCost(coffee)).sum()).sum();

        return revenueCustomCoffeeTotal - costCustomCoffeeTotal;
    }

    @Override
    public Double getTotalOrderPrice(Order order) {

        return order.getCoffeeList()
                .stream()
                .mapToDouble(coffee -> this.coffeeService.getCoffeePrice(coffee))
                .sum();
    }

    @Override
    public Double getTotalProfitForToday() {

        Collection<Order> orderCollection = findAll();

        double revenueToday = orderCollection.stream()
                .filter(order -> order.getOrderDateTime()
                        .isAfter(LocalDateTime.now().with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay())))
                .mapToDouble(order -> order.getCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeePrice(coffee)).sum()).sum();

        double costToday = orderCollection.stream()
                .filter(order -> order.getOrderDateTime()
                        .isAfter(LocalDateTime.now().with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay())))
                .mapToDouble(order -> order.getCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeeCost(coffee)).sum()).sum();

        return revenueToday - costToday;
    }

    @Override
    public void deleteOrder(Integer id) {

        this.orderRepository.findById(id).ifPresentOrElse(
                foundOrder -> orderRepository.delete(foundOrder),
                () -> {
                    throw new NoSuchElementException("The specified element does not exist");
                });
    }

    public Map<Ingredient, Integer> getAllIngredientsForOrder(Order order) {

        Map<Ingredient, Integer> allIngredientsForOrder = new HashMap<>();

        for (Coffee coffee : order.getCoffeeList()) {
            this.coffeeService.getAllCoffeeIngredients(coffee).forEach(
                    (ingredient, shots) -> {
                        if (allIngredientsForOrder.containsKey(ingredient)) {
                            int existingNumberOfShots = allIngredientsForOrder.get(ingredient);
                            allIngredientsForOrder.put(ingredient, existingNumberOfShots + shots);
                        } else {
                            allIngredientsForOrder.putIfAbsent(ingredient, shots);
                        }
                    });
        }

        return allIngredientsForOrder;
    }
}