package org.fantasticcoffee.shop.repository.memory;

import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("ingredientsRepositoryMemory")
public class DefaultIngredientRepository {

    private List<IngredientInStock> database;

    public DefaultIngredientRepository() {
        this.database = new ArrayList<>();
    }

    public boolean save(IngredientInStock ingredient) {

        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient cannot be null");
        }
        return database.add(ingredient);
    }

    public Optional<IngredientInStock> find(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return Optional.ofNullable(database.get(id));
    }

    public IngredientInStock find(IngredientInStock ingredient) {

        return database.stream()
                .filter(ingredientInStock -> ingredientInStock == ingredient)
                .findFirst()
                .orElseThrow();
    }

    public Optional<IngredientInStock> update(Integer id, IngredientInStock ingredient) {
        return Optional.ofNullable(database.set(id, ingredient));
    }

    public List<IngredientInStock> findAll() {
        return database;
    }
}