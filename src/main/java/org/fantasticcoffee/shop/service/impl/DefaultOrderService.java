package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;
import org.fantasticcoffee.shop.repository.memory.Repository;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.fantasticcoffee.shop.service.IngredientService;
import org.fantasticcoffee.shop.service.OrderService;
import org.fantasticcoffee.shop.validator.CardValidation;
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
    private Repository<Order> orderRepository;
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private CardValidation cardValidation;

    private static Integer id = 0;

    @Override
    public Order placeOrder(Order order) {

        Map<IngredientInStock, Integer> allIngredientsInOrder = ingredientService.checkIngredientInStockForOrder(order);
        this.cardValidation.cardNumberValidation(order.getCard().getCardNumber());

        order.setId(++DefaultOrderService.id);
        order.setOrderDateTime(LocalDateTime.now());
        order.setPrice(getTotalOrderPrice(order));

        order.getCard().setCardNumber(null);
        order.getCard().setCiv(null);

        Optional<Order> result = this.orderRepository.save(order);
        if (result.isPresent()) {
            DefaultOrderService.id--;
            return null;
        } else {
            this.ingredientService.decrementIngredient(allIngredientsInOrder);
            return order.duplicate();
        }
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

        Optional<Order> result = this.orderRepository.find(id);
        return result.map(Order::duplicate).orElseThrow();
    }

    @Override
    public Order update(Integer id, Order order) {

        if (order == null) {
            throw new IllegalArgumentException("Illegal attempt! Please specify one of the orders you want to update");
        }

        Optional<Order> oldOrder = this.orderRepository.find(id);
        if (oldOrder.isEmpty()) {
            throw new NoSuchElementException("The order to change was not found");
        }

        order.setOrderDateTime(LocalDateTime.now());
        order.setId(id);
        Optional<Order> result = this.orderRepository.update(order);

        return result.map(Order::duplicate).orElse(null);
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

        this.orderRepository.find(id).ifPresentOrElse(foundOrder -> {
                    orderRepository.delete(id);
                    DefaultOrderService.id--;
                },
                () -> {
                    throw new NoSuchElementException("The specified element does not exist");
                });
    }

    public Map<IngredientInStock, Integer> getAllIngredientsForOrder(Order order) {

        Map<IngredientInStock, Integer> allIngredientsForOrder = new HashMap<>();

        for (Coffee coffee : order.getCoffeeList()) {
            this.coffeeService.getAllCoffeeIngredients(coffee).forEach(
                    (ingredientInStock, shots) -> {
                        if (allIngredientsForOrder.containsKey(ingredientInStock)) {
                            int existingNumberOfShots = allIngredientsForOrder.get(ingredientInStock);
                            allIngredientsForOrder.put(ingredientInStock, existingNumberOfShots + shots);
                        } else {
                            allIngredientsForOrder.putIfAbsent(ingredientInStock, shots);
                        }
                    });
        }

        return allIngredientsForOrder;
    }
}