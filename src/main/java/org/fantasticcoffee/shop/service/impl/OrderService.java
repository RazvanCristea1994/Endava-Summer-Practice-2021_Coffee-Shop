package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.repository.IRepository;
import org.fantasticcoffee.shop.service.ICoffee;
import org.fantasticcoffee.shop.service.IOrder;
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
public class OrderService implements IOrder {

    @Autowired
    private IRepository<Order> repository;
    @Autowired
    private ICoffee coffeeService;

    private static Integer id = -1;

    public OrderService() {
    }

    public Order placeOrder(Order order) {

        order.setId(++OrderService.id);
        order.setOrderDateTime(LocalDateTime.now());

        Optional<Order> result = this.repository.save(order);
        if (!result.isEmpty()) {
            OrderService.id--;
            return null;
        } else {
            return Order.copyOrderObject(order);
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
        if (result.isPresent()) {
            return Order.copyOrderObject(result.get());
        }
        return null;
    }

    public Order update(Order order) {

        order.setOrderDateTime(LocalDateTime.now());
        Optional<Order> result = this.repository.update(order);
        if (result.isPresent()) {
            return Order.copyOrderObject(order);
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
            OrderService.id--;
            return Order.copyOrderObject(result.get());
        }
        return null;
    }
}
