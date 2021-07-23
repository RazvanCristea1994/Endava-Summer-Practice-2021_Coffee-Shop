package org.fantasticcoffee.shop.service.impl;

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
    private Repository<Order> repository;
    @Autowired
    private CoffeeService coffeeService;

    private static Integer id = -1;

    public DefaultOrderService() {
    }

    public Order placeOrder(Order order) {

        order.setId(++DefaultOrderService.id);
        order.setOrderDateTime(LocalDateTime.now());

        Optional<Order> result = this.repository.save(order);

        Order oo = order.duplicate();
        if (!result.isEmpty()) {
            DefaultOrderService.id--;
            return null;
        } else {
            return order.duplicate();
        }
    }

    public List<Order> findAll() {

        Iterable<Order> orders = this.repository.findAll();
        List<Order> orderList = new ArrayList<>();
        orders.forEach(orderList::add);

        return orderList;
    }

    public Order findOrder(Integer id) {

        Optional<Order> result = this.repository.find(id);
        return result.map(Order::duplicate).orElse(null);
    }

    public Order update(Order order) {

        order.setOrderDateTime(LocalDateTime.now());
        Optional<Order> result = this.repository.update(order);

        if (result.isPresent()) {
            return order.duplicate();
        }
        return null;
    }

    public Double getTotalProfit() {

        Collection<Order> orderCollection = findAll();
        Double revenueTotal = orderCollection.stream()
                .mapToDouble(order -> order.getCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeePrice(coffee)).sum()).sum();

        Double costTotal = orderCollection.stream()
                .mapToDouble(order -> order.getCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeeCost(coffee)).sum()).sum();

        return revenueTotal - costTotal;
    }

    public Double getTotalOrderPrice(Order order) {
        return order.getCoffeeList().stream().mapToDouble(coffee -> this.coffeeService.getCoffeePrice(coffee)).sum();
    }

    public Double getTotalProfitForToday() {

        Collection<Order> orderCollection = findAll();

        Double revenueToday = orderCollection.stream()
                .filter(order -> order.getOrderDateTime()
                        .isAfter(LocalDateTime.now().with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay())))
                .mapToDouble(order -> order.getCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeePrice(coffee)).sum()).sum();

        Double costToday = orderCollection.stream()
                .filter(order -> order.getOrderDateTime()
                        .isAfter(LocalDateTime.now().with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay())))
                .mapToDouble(order -> order.getCoffeeList().stream()
                        .mapToDouble(coffee ->
                                this.coffeeService.getCoffeeCost(coffee)).sum()).sum();

        return revenueToday - costToday;
    }

    public Order deleteOrder(Integer id) {

        Optional<Order> result = repository.delete(id);
        if (result.isPresent()) {
            DefaultOrderService.id--;
            return result.get().duplicate();
        }
        return null;
    }
}