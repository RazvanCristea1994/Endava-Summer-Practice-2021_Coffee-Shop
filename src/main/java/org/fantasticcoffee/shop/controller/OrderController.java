package org.fantasticcoffee.shop.controller;

import org.fantasticcoffee.shop.data.order.OrderRequest;
import org.fantasticcoffee.shop.data.order.OrderResponse;
import org.fantasticcoffee.shop.facade.order.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderFacade orderFacade;

    @PostMapping("/pay")
    @ResponseBody
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {

        try {
            return this.orderFacade.placeOrder(orderRequest);
        } catch (IllegalArgumentException e) {
            return new OrderResponse();
        }
    }
}