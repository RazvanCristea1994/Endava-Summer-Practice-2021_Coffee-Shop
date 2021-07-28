package org.fantasticcoffee.shop.facade.order;

import org.fantasticcoffee.shop.data.order.OrderRequest;
import org.fantasticcoffee.shop.data.order.OrderResponse;

public interface OrderFacade {

    OrderResponse placeOrder(OrderRequest orderRequest);
}