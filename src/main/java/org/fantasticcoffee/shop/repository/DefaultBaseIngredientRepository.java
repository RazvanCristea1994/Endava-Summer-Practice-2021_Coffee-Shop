package org.fantasticcoffee.shop.repository;

import org.fantasticcoffee.shop.model.ingredientdefinition.BaseIngredient;
import org.fantasticcoffee.shop.model.stock.BaseIngredientInStock;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("baseIngredientsRepository")
public class DefaultBaseIngredientRepository {

    private List<BaseIngredientInStock> database;

    public DefaultBaseIngredientRepository() {
        this.database = new ArrayList<>();
    }

    public boolean save(BaseIngredientInStock ingredient) {

        if (ingredient == null) {
            throw new IllegalArgumentException("ExtraIngredient cannot be null");
        }
        return database.add(ingredient);
    }

    public Optional<BaseIngredientInStock> find(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return Optional.ofNullable(database.get(id));
    }

    public BaseIngredientInStock find(BaseIngredient ingredient) {

        return database.stream()
                .filter(baseIngredientInStock -> baseIngredientInStock.getBaseIngredient() == ingredient)
                .findFirst()
                .orElseThrow();
    }

    public Optional<BaseIngredientInStock> update(Integer id, BaseIngredientInStock ingredient) {
        return Optional.ofNullable(database.set(id, ingredient));
    }

    public List<BaseIngredientInStock> findAll() {
        return database;
    }
}