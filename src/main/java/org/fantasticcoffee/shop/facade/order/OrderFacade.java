package org.fantasticcoffee.shop.facade.order;

import org.fantasticcoffee.shop.data.order.OrderRequest;
import org.fantasticcoffee.shop.data.order.OrderResponse;

import java.util.List;

public interface OrderFacade {

    OrderResponse placeOrder(OrderRequest orderRequest);

    List<OrderResponse> getAll();

    OrderResponse update(Integer id, OrderRequest orderRequest);

    void delete(Integer id);
}