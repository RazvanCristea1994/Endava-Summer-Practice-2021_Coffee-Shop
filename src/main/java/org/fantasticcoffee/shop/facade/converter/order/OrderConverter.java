package org.fantasticcoffee.shop.facade.converter.order;

import org.fantasticcoffee.shop.data.order.OrderRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.facade.populator.Populator;
import org.fantasticcoffee.shop.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter implements Converter<Order, OrderRequest> {

    @Autowired
    private Populator<Order.Builder, OrderRequest> populator;

    @Override
    public Order convert(OrderRequest orderRequest) {

        Order.Builder order = new Order.Builder();
        populator.populate(order, orderRequest);

        return order.build();
    }
}