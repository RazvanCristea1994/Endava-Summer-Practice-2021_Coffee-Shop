package org.fantasticcoffee.shop.repository.memory;

import org.fantasticcoffee.shop.model.Order;
import org.springframework.stereotype.Repository;

@Repository("orderRepositoryMemory")
public class DefaultOrderRepository extends DefaultInMemoryRepository<Order> {

    @Override
    public Integer getIdForEntity(Order order) {
        return order.getId();
    }
}