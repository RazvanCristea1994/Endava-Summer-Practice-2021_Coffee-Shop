package org.fantasticcoffee.shop.repository.database;

import org.fantasticcoffee.shop.model.recipe.StandardRecipeInStock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandardRecipeInStockRepository extends CrudRepository<StandardRecipeInStock, Integer> {

    StandardRecipeInStock findByName(String standardRecipeName);
}