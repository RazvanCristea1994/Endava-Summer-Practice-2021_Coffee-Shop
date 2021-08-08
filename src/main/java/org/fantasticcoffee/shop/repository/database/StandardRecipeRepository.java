package org.fantasticcoffee.shop.repository.database;

import org.fantasticcoffee.shop.model.StandardRecipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandardRecipeRepository extends CrudRepository<StandardRecipe, Integer> {

    StandardRecipe findByName(String standardRecipeName);
}