package org.fantasticcoffee.shop.repository;

import org.fantasticcoffee.shop.model.ingredientdefinition.ExtraIngredient;
import org.fantasticcoffee.shop.model.stock.ExtraIngredientInStock;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("extraIngredientsRepository")
public class DefaultExtraIngredientRepository {

    private List<ExtraIngredientInStock> database;

    public DefaultExtraIngredientRepository() {
        this.database = new ArrayList<>();
    }

    public boolean save(ExtraIngredientInStock ingredient) {

        if (ingredient == null) {
            throw new IllegalArgumentException("ExtraIngredient cannot be null");
        }
        return database.add(ingredient);
    }

    public Optional<ExtraIngredientInStock> find(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return Optional.ofNullable(database.get(id));
    }

    public ExtraIngredientInStock find(ExtraIngredient ingredient) {
        return database.stream()
                .filter(extraIngredientInStock -> extraIngredientInStock.getExtraIngredient() == ingredient)
                .findFirst()
                .orElseThrow();
    }

    public Optional<ExtraIngredientInStock> update(Integer id, ExtraIngredientInStock ingredient) {
        return Optional.ofNullable(database.set(id, ingredient));
    }

    public List<ExtraIngredientInStock> findAll() {
        return database;
    }
}