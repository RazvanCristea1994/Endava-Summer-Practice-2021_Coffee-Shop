package org.fantasticcoffee.shop.repository.database;

import org.fantasticcoffee.shop.model.Coffee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends CrudRepository<Coffee, Integer> {
}