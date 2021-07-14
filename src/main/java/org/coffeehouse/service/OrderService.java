package org.coffeehouse.service;

import org.coffeehouse.model.Coffee;
import org.coffeehouse.model.Order;
import org.coffeehouse.repository.IRepository;

import java.time.LocalDateTime;
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

        order.setId(OrderService.id++);
        order.setTotalCost(order.getOrderedCoffeeList()
                .stream()
                .mapToDouble(Coffee::getPrice)
                .sum());
        order.setOrderDateTime(LocalDateTime.now());

        try {
            Optional<Order> result = this.repository.save(order);
            return order;
        } catch (Exception e) {
            OrderService.id--;
            return null;
        }
    }
}
