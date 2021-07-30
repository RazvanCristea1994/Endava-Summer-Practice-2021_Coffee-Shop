package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.repository.Repository;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.fantasticcoffee.shop.service.OrderService;
import org.fantasticcoffee.shop.validator.CardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.*;

@Service("orderService")
public class DefaultOrderService implements OrderService {

    @Autowired
    private Repository<Order> orderRepository;
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private DefaultIngredientService ingredientService;
    @Autowired
    private CardValidator cardValidator;

    private static Integer id = 0;

    public Order placeOrder(Order order) {

        this.cardValidator.validateCard(order.getCard());

        order.setId(++DefaultOrderService.id);
        order.setOrderDateTime(LocalDateTime.now());

        Optional<Order> result = this.orderRepository.save(order);
        if (!result.isEmpty()) {
            DefaultOrderService.id--;
            return null;
        } else {
            decrementIngredientsInRepo(order);
            return order.duplicate();
        }
    }

    public List<Order> findAll() {

        Iterable<Order> orders = this.orderRepository.findAll();
        List<Order> orderList = new ArrayList<>();
        orders.forEach(orderList::add);

        return orderList;
    }

    public Order findOrder(Integer id) {

        Optional<Order> result = this.orderRepository.find(id);
        return result.map(Order::duplicate).orElseThrow();
    }

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

    public Double getTotalProfit() {

        Collection<Order> orderCollection = findAll();
        Double revenueCustomCoffeeTotal = orderCollection.stream()
                .mapToDouble(order -> order.getCustomCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeePrice(coffee)).sum()).sum();

        Double revenueCustomizableStandardCoffeeTotal = orderCollection.stream()
                .mapToDouble(order -> order.getCustomizableStandardCoffee().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeePrice(coffee)).sum()).sum();

        Double costCustomCoffeeTotal = orderCollection.stream()
                .mapToDouble(order -> order.getCustomCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeeCost(coffee)).sum()).sum();

        Double costCustomizableStandardCoffeeTotal = orderCollection.stream()
                .mapToDouble(order -> order.getCustomizableStandardCoffee().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeeCost(coffee)).sum()).sum();

        return revenueCustomCoffeeTotal + revenueCustomizableStandardCoffeeTotal - costCustomCoffeeTotal - costCustomizableStandardCoffeeTotal;
    }

    public Double getTotalOrderPrice(Order order) {
        Double customCoffeeTotalOrderPrice = order.getCustomCoffeeList().stream()
                .mapToDouble(coffee -> this.coffeeService.getCoffeePrice(coffee))
                .sum();

        Double customizableStandardCoffeeTotalOrderPrice = order.getCustomizableStandardCoffee().stream()
                .mapToDouble(coffee -> this.coffeeService.getCoffeePrice(coffee))
                .sum();

        return customCoffeeTotalOrderPrice + customizableStandardCoffeeTotalOrderPrice;
    }

    public Double getTotalProfitForToday() {

        Collection<Order> orderCollection = findAll();

        Double revenueCustomCoffeeToday = orderCollection.stream()
                .filter(order -> order.getOrderDateTime()
                        .isAfter(LocalDateTime.now().with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay())))
                .mapToDouble(order -> order.getCustomCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeePrice(coffee)).sum()).sum();

        Double revenueCustomizableStandardCoffeeToday = orderCollection.stream()
                .filter(order -> order.getOrderDateTime()
                        .isAfter(LocalDateTime.now().with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay())))
                .mapToDouble(order -> order.getCustomizableStandardCoffee().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeePrice(coffee)).sum()).sum();

        Double costCustomCoffeeToday = orderCollection.stream()
                .filter(order -> order.getOrderDateTime()
                        .isAfter(LocalDateTime.now().with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay())))
                .mapToDouble(order -> order.getCustomCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeeCost(coffee)).sum()).sum();

        Double costCustomizableStandardCoffeeToday = orderCollection.stream()
                .filter(order -> order.getOrderDateTime()
                        .isAfter(LocalDateTime.now().with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay())))
                .mapToDouble(order -> order.getCustomizableStandardCoffee().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeeCost(coffee)).sum()).sum();

        return revenueCustomCoffeeToday + revenueCustomizableStandardCoffeeToday - costCustomCoffeeToday - costCustomizableStandardCoffeeToday;
    }

    public void deleteOrder(Integer id) {

        this.orderRepository.find(id).ifPresentOrElse(foundOrder -> {
                    orderRepository.delete(id);
                    DefaultOrderService.id--;
                },
                () -> {
                    throw new NoSuchElementException("The specified element does not exist");
                });
    }

    private void decrementIngredientsInRepo(Order order) {

        order.getCustomizableStandardCoffee().forEach(coffee -> {
            this.ingredientService.decrementBaseIngredient(coffee.getStandardCoffee().getRecipe().getBaseIngredients());
            this.ingredientService.decrementExtraIngredient(coffee.getStandardCoffee().getRecipe().getExtraIngredients());
            this.ingredientService.decrementExtraIngredient(coffee.getExtraIngredients());
        });

        order.getCustomCoffeeList().forEach(coffee -> {
            this.ingredientService.decrementBaseIngredient(coffee.getCustomerMadeRecipe().getBaseIngredients());
            this.ingredientService.decrementExtraIngredient(coffee.getCustomerMadeRecipe().getExtraIngredients());
        });
    }
}