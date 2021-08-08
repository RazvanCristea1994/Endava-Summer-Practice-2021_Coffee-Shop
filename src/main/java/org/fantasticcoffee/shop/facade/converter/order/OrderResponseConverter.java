package org.fantasticcoffee.shop.facade.converter.order;

import org.fantasticcoffee.shop.data.card.CardResponse;
import org.fantasticcoffee.shop.data.customcoffee.CoffeeResponse;
import org.fantasticcoffee.shop.data.order.OrderResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Card;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderResponseConverter implements Converter<OrderResponse, Order> {

    @Autowired
    private Converter<CoffeeResponse, Coffee> coffeeResponseConverter;
    @Autowired
    private Converter<CardResponse, Card> cardResponseConverter;
    @Autowired
    private OrderService orderService;

    @Override
    public OrderResponse convert(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                order.getOrderDateTime(),
                this.coffeeResponseConverter.convertAll(order.getCoffeeList()),
                order.getOrderType(),
                this.cardResponseConverter.convert(order.getCard()),
                order.getPrice(),
                this.orderService.getTotalProfitForToday());
    }
}