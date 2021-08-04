package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.coffee.CoffeeWithStandardRecipeBase;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.fantasticcoffee.shop.model.ingredient.Ingredient;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;
import org.fantasticcoffee.shop.repository.memory.Repository;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.fantasticcoffee.shop.service.OrderService;
import org.fantasticcoffee.shop.validator.CardValidation;
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
    private CardValidation cardValidation;

    private static Integer id = 0;

    @Override
    public Order placeOrder(Order order, List<IngredientOnRecipe> allIngredientsInOrder) {

        this.cardValidation.cardNumberValidation(order.getCard().getCardNumber());

        order.setId(++DefaultOrderService.id);
        order.setOrderDateTime(LocalDateTime.now());
        order.setPrice(getTotalOrderPrice(order));

        order.getCard().setCardNumber(null);
        order.getCard().setCiv(null);

        Optional<Order> result = this.orderRepository.save(order);
        if (!result.isEmpty()) {
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

        Collection<Order> orderCollection = findAll();

        double revenueCustomCoffeeTotal = orderCollection.stream()
                .mapToDouble(order -> order.getCustomCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeePrice(coffee)).sum()).sum();

        double revenueCustomizableStandardCoffeeTotal = orderCollection.stream()
                .mapToDouble(order -> order.getCoffeeWithStandardRecipeBase().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeePrice(coffee)).sum()).sum();

        double costCustomCoffeeTotal = orderCollection.stream()
                .mapToDouble(order -> order.getCustomCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeeCost(coffee)).sum()).sum();

        double costCustomizableStandardCoffeeTotal = orderCollection.stream()
                .mapToDouble(order -> order.getCoffeeWithStandardRecipeBase().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeeCost(coffee)).sum()).sum();

        return revenueCustomCoffeeTotal + revenueCustomizableStandardCoffeeTotal - costCustomCoffeeTotal - costCustomizableStandardCoffeeTotal;
    }

    @Override
    public Double getTotalOrderPrice(Order order) {
        double customCoffeeTotalOrderPrice = order.getCustomCoffeeList()
                .stream()
                .mapToDouble(coffee -> this.coffeeService.getCoffeePrice(coffee))
                .sum();

        double customizableStandardCoffeeTotalOrderPrice = order.getCoffeeWithStandardRecipeBase()
                .stream()
                .mapToDouble(coffee -> this.coffeeService.getCoffeePrice(coffee))
                .sum();

        return customCoffeeTotalOrderPrice + customizableStandardCoffeeTotalOrderPrice;
    }

    @Override
    public Double getTotalProfitForToday() {

        Collection<Order> orderCollection = findAll();

        double revenueCustomCoffeeToday = orderCollection.stream()
                .filter(order -> order.getOrderDateTime()
                        .isAfter(LocalDateTime.now().with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay())))
                .mapToDouble(order -> order.getCustomCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeePrice(coffee)).sum()).sum();

        double revenueCustomizableStandardCoffeeToday = orderCollection.stream()
                .filter(order -> order.getOrderDateTime()
                        .isAfter(LocalDateTime.now().with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay())))
                .mapToDouble(order -> order.getCoffeeWithStandardRecipeBase().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeePrice(coffee)).sum()).sum();

        double costCustomCoffeeToday = orderCollection.stream()
                .filter(order -> order.getOrderDateTime()
                        .isAfter(LocalDateTime.now().with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay())))
                .mapToDouble(order -> order.getCustomCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeeCost(coffee)).sum()).sum();

        double costCustomizableStandardCoffeeToday = orderCollection.stream()
                .filter(order -> order.getOrderDateTime()
                        .isAfter(LocalDateTime.now().with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay())))
                .mapToDouble(order -> order.getCoffeeWithStandardRecipeBase().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeeCost(coffee)).sum()).sum();

        return revenueCustomCoffeeToday + revenueCustomizableStandardCoffeeToday - costCustomCoffeeToday - costCustomizableStandardCoffeeToday;
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

    public List<IngredientOnRecipe> getAllIngredientsForOrder(Order order) {

        Map<Ingredient, IngredientOnRecipe> allIngredientsForOrder = new HashMap<>();

        for (CustomCoffee customCoffee : order.getCustomCoffeeList()) {
            for (IngredientOnRecipe ingredientOnRecipe : this.coffeeService.getAllIngredientsForCustomCoffee(customCoffee)) {
                if (allIngredientsForOrder.containsKey(ingredientOnRecipe.getIngredient())) {
                    IngredientOnRecipe ingredient = allIngredientsForOrder.get(ingredientOnRecipe.getIngredient());
                    ingredient.setNumberOfShots(ingredient.getNumberOfShots() + ingredient.getNumberOfShots());
                } else {
                    allIngredientsForOrder.putIfAbsent(ingredientOnRecipe.getIngredient(), ingredientOnRecipe);
                }
            }
        }

        for (CoffeeWithStandardRecipeBase coffeeWithStandardRecipeBase : order.getCoffeeWithStandardRecipeBase()) {
            for (IngredientOnRecipe ingredientOnRecipe : this.coffeeService.getAllIngredientsForCoffeeWithStandardRecipeBase(coffeeWithStandardRecipeBase)) {
                if (allIngredientsForOrder.containsKey(ingredientOnRecipe.getIngredient())) {
                    IngredientOnRecipe ingredient = allIngredientsForOrder.get(ingredientOnRecipe.getIngredient());
                    ingredient.setNumberOfShots(ingredient.getNumberOfShots() + ingredient.getNumberOfShots());
                } else {
                    allIngredientsForOrder.putIfAbsent(ingredientOnRecipe.getIngredient(), ingredientOnRecipe);
                }
            }
        }

        return new ArrayList<>(allIngredientsForOrder.values());
    }
}