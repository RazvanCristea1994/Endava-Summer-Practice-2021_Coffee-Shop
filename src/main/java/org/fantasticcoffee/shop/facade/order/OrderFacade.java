package org.fantasticcoffee.shop.facade.order;

import org.fantasticcoffee.shop.data.order.OrderRequest;
import org.fantasticcoffee.shop.data.order.OrderResponse;
import org.fantasticcoffee.shop.model.Order;

import java.util.List;

public interface OrderFacade {

    Order getOrder(OrderRequest orderRequest);

    OrderResponse getOrderResponse(Order order);

    List<OrderResponse> getAll();

    OrderResponse update(Integer id, OrderRequest orderRequest);

    void delete(Integer id);
}