package org.fantasticcoffee.shop.repository.database;

import org.fantasticcoffee.shop.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {
}
