package org.coffeehouse.service;

import org.coffeehouse.model.Coffee;
import org.coffeehouse.model.Order;
import org.coffeehouse.repository.IRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class OrderService implements IOrder {

    private final IRepository<Long, Order> repository;

    private static Long id = -1L;

    public OrderService(IRepository<Long, Order> repository) {
        this.repository = repository;
    }

    public Order placeOrder(Order order) {

        order.setId(++OrderService.id);
        order.setOrderDateTime(LocalDateTime.now());

        Optional<Order> result = this.repository.save(order);
        if (!result.isEmpty()) {
            OrderService.id--;
            return null;
        } else {
            return new Order(order);
        }
    }

    public List<Order> findAll() {

        Iterable<Order> orders = this.repository.findAll();
        List<Order> orderList = new ArrayList<>();
        orders.forEach(orderList::add);

        return orderList;
    }

    public Order findOrder(Long id) {

        Optional<Order> result = this.repository.find(id);
        if (result.isPresent()) {
            return new Order(result.get());
        }
        return null;
    }

    public Order update(Order order) {

        order.setOrderDateTime(LocalDateTime.now());
        Optional<Order> result = this.repository.update(order);
        if (result.isPresent()) {
            return new Order(result.get());
        }
        return null;
    }

    public Double getTotalProfit() {

        Collection<Order> orderCollection = findAll();
        Double revenueTotal = orderCollection.stream().mapToDouble(Order::getTotalRevenue).sum();
        Double costTotal = orderCollection.stream()
                .flatMap(coffee -> coffee.getOrderCoffeeList().stream())
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
                .flatMap(coffee -> coffee.getOrderCoffeeList().stream())
                .mapToDouble(Coffee::getCost).sum();

        return revenueToday - costToday;
    }

    public Order deleteOrder(Long id) {

        Optional<Order> result = repository.delete(id);
        if (result.isPresent()) {
            OrderService.id--;
            return new Order(result.get());
        }
        return null;
    }
}
