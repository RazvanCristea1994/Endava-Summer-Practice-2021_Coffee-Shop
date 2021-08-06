package org.fantasticcoffee.shop.facade.order;

import org.fantasticcoffee.shop.data.order.OrderRequest;
import org.fantasticcoffee.shop.data.order.OrderResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultOrderFacade implements OrderFacade {

    @Autowired
    Converter<Order, OrderRequest> orderRequestConverter;
    @Autowired
    Converter<OrderResponse, Order> orderResponseConverter;
    @Autowired
    OrderService orderService;

    @Override
    public Order getOrder(OrderRequest orderRequest) {

        return this.orderRequestConverter.convert(orderRequest);
    }

    @Override
    public OrderResponse getOrderResponse(Order order) {

        return this.orderResponseConverter.convert(order);
    }

    @Override
    public List<OrderResponse> getAll() {
        return this.orderResponseConverter.convertAll(this.orderService.findAll());
    }

    @Override
    public OrderResponse update(Integer id, OrderRequest orderRequest) {

        Order order = this.orderService.update(id, this.orderRequestConverter.convert(orderRequest));
        return orderResponseConverter.convert(order);
    }

    @Override
    public void delete(Integer id) {
        this.orderService.deleteOrder(id);
    }
}