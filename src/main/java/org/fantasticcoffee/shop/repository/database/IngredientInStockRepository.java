package org.fantasticcoffee.shop.repository.database;

import org.fantasticcoffee.shop.model.ingredient.Ingredient;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientInStockRepository extends CrudRepository<IngredientInStock, Integer> {

    IngredientInStock findByIngredient(Ingredient ingredient);
}