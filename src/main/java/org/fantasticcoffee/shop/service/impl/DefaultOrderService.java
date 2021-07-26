package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.converter.BaseIngredientOnRecipeConverter;
import org.fantasticcoffee.shop.converter.ExtraIngredientOnRecipeConverter;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.repository.Repository;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.fantasticcoffee.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("orderService")
public class DefaultOrderService implements OrderService {

    @Autowired
    private Repository<Order> orderRepository;
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private DefaultBaseIngredientService baseIngredientService;
    @Autowired
    private DefaultExtraIngredientService extraIngredientService;
    @Autowired
    private BaseIngredientOnRecipeConverter baseIngredientConverter;
    @Autowired
    private ExtraIngredientOnRecipeConverter extraIngredientConverter;

    private static Integer id = 0;

    public Order placeOrder(Order order) {

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
        return result.map(Order::duplicate).orElse(null);
    }

    public Order update(Order order) {

        order.setOrderDateTime(LocalDateTime.now());
        Optional<Order> result = this.orderRepository.update(order);

        if (result.isPresent()) {
            return null;
        }
        return order.duplicate();
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

    public Order deleteOrder(Integer id) {

        Optional<Order> result = orderRepository.delete(id);
        if (result.isPresent()) {
            DefaultOrderService.id--;
            return result.get().duplicate();
        }
        return null;
    }

    private void decrementIngredientsInRepo(Order order) {

        order.getCustomizableStandardCoffee().forEach(coffee -> {
            this.baseIngredientService.decrementBaseIngredient(
                    this.baseIngredientConverter.recipeIngredientsToStockIngredients(coffee.getStandardCoffee().getRecipe().getBaseIngredients()));
            this.extraIngredientService.decrementExtraIngredient(
                    this.extraIngredientConverter.recipeIngredientsToStockIngredients(coffee.getStandardCoffee().getRecipe().getExtraIngredients()));
            this.extraIngredientService.decrementExtraIngredient(
                    this.extraIngredientConverter.recipeIngredientsToStockIngredients(coffee.getExtraIngredients()));
        });

        order.getCustomCoffeeList().forEach(coffee -> {
            this.baseIngredientService.decrementBaseIngredient(
                    this.baseIngredientConverter.recipeIngredientsToStockIngredients(coffee.getCustomerMadeRecipe().getBaseIngredients()));
            this.extraIngredientService.decrementExtraIngredient(
                    this.extraIngredientConverter.recipeIngredientsToStockIngredients(coffee.getCustomerMadeRecipe().getExtraIngredients()));
        });
    }
}