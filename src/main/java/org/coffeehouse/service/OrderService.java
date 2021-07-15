package org.coffeehouse.service;

import org.coffeehouse.model.Coffee;
import org.coffeehouse.model.Order;
import org.coffeehouse.repository.IRepository;

import java.time.*;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class OrderService implements IOrder {

    private final IRepository<Long, Order> repository;

    private static Long id = -1L;

    public OrderService(IRepository<Long, Order> repository) {
        this.repository = repository;
    }

    public Order placeOrder(Order order) {

        order.setId(++OrderService.id);
        order.setTotalRevenue(order.getOrderedCoffeeList()
                .stream()
                .mapToDouble(Coffee::getPrice)
                .sum());
        order.setOrderDateTime(LocalDateTime.now());

        order.setOrderDateTime(LocalDateTime.now());

        try {
            Optional<Order> result = this.repository.save(order);
            return order;
        } catch (Exception e) {
            OrderService.id--;
            return null;
        }
    }

    public Collection<Order> findAll() {

        Iterable<Order> orders = this.repository.findAll();
        Collection<Order> orderCollection = new ArrayList<>();
        orders.forEach(orderCollection::add);

        return orderCollection;
    }

    public Double getTotalProfit() {

        Collection<Order> orderCollection = findAll();
        Double revenueTotal = orderCollection.stream().mapToDouble(Order::getTotalRevenue).sum();
        Double costTotal = orderCollection.stream()
                .flatMap(coffee -> coffee.getOrderedCoffeeList().stream())
                .mapToDouble(Coffee::getCost).sum();

        return revenueTotal - costTotal;
    }

    public Double getTotalProfitForToday() {

        Collection<Order> orderCollection = findAll();

        Double revenueToday = orderCollection.stream()
                .filter(order -> order.getOrderDateTime()
                        .isAfter(LocalDateTime.now().with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay())))
                .mapToDouble(Order::getTotalRevenue).sum();

        Double costToday = orderCollection.stream()
                .filter(order -> order.getOrderDateTime()
                        .isAfter(LocalDateTime.now().with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay())))
                .flatMap(coffee -> coffee.getOrderedCoffeeList().stream())
                .mapToDouble(Coffee::getCost).sum();

        return revenueToday - costToday;
    }

    public Order cancelOrder(Long id) {

        Optional<Order> result = repository.delete(id);
        return result.orElse(null);
    }
}
