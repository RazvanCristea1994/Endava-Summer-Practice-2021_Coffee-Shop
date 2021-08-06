package org.fantasticcoffee.shop.facade.converter.order;

import org.fantasticcoffee.shop.data.card.CardRequest;
import org.fantasticcoffee.shop.data.customcoffee.CoffeeRequest;
import org.fantasticcoffee.shop.data.order.OrderRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Card;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderConverter implements Converter<Order, OrderRequest> {

    @Autowired
    private Converter<Coffee, CoffeeRequest> coffeeConverter;
    @Autowired
    private Converter<Card, CardRequest> cardConverter;

    @Override
    public Order convert(OrderRequest orderRequest) {

        Order.Builder order = new Order.Builder();

        order.setCustomerName(orderRequest.getCustomerName());
        if (orderRequest.getCoffeeList() != null) {
            order.setCoffeeList(this.coffeeConverter.convertAll(orderRequest.getCoffeeList()));
        } else {
            order.setCoffeeList(new ArrayList<>());
        }
        order.setCard(this.cardConverter.convert(orderRequest.getCardRequest()));
        order.setWhereToDrink(orderRequest.getWhereToDrink());

        return order.build();
    }
}