package org.fantasticcoffee.shop.facade.order;

import org.fantasticcoffee.shop.data.order.OrderRequest;
import org.fantasticcoffee.shop.data.order.OrderResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultOrderFacade implements OrderFacade {

    @Autowired
    Converter<Order, OrderRequest> orderRequestConverter;

    @Autowired
    Converter<OrderResponse, Order> orderConverter;

    @Autowired
    OrderService orderService;

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = orderRequestConverter.convert(orderRequest);
        Order placedOrder = orderService.placeOrder(order);
        return orderConverter.convert(placedOrder);
    }
}