package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Recipe {

    private List<IngredientOnRecipe> ingredients = new ArrayList<>();

    private Recipe(Builder builder) {
        this.ingredients = builder.ingredientsConfig;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Builder {

        private List<IngredientOnRecipe> ingredientsConfig;

        public Builder(List<IngredientOnRecipe> ingredients) {
            this.ingredientsConfig = ingredients;
        }

        public void addIngredient(IngredientOnRecipe ingredient) {
            this.ingredientsConfig.add(ingredient);
        }

        public Recipe build() {
            return new Recipe(this);
        }
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        if (!ingredients.isEmpty()) {
            getIngredients(stringBuilder);
        }

        return String.valueOf(stringBuilder);
    }

    private void getIngredients(StringBuilder stringBuilder) {
        ingredients.forEach(i ->
                stringBuilder.append(String.format("%s %s %s %-3s", i.getQuantity(), "x [", i.getIngredient().getIngredientName(), "]")));
    }
}