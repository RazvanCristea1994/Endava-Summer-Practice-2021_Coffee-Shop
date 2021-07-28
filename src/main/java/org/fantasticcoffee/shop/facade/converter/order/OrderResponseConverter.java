package org.fantasticcoffee.shop.facade.converter.order;

import org.fantasticcoffee.shop.data.order.OrderResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.facade.populator.Populator;
import org.fantasticcoffee.shop.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderResponseConverter implements Converter<OrderResponse, Order> {

    @Autowired
    private Populator<OrderResponse, Order> populator;

    @Override
    public OrderResponse convert(Order order) {

        OrderResponse orderResponse = new OrderResponse();
        populator.populate(orderResponse, order);

        return orderResponse;
    }
}