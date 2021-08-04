package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Recipe {

    private List<IngredientOnRecipe> ingredientOnRecipe = new ArrayList<>();

    private Recipe(Builder builder) {
        this.ingredientOnRecipe = builder.ingredientsConfig;
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
        if (!ingredientOnRecipe.isEmpty()) {
            getIngredientOnRecipe(stringBuilder);
        }

        return String.valueOf(stringBuilder);
    }

    private void getIngredientOnRecipe(StringBuilder stringBuilder) {
        ingredientOnRecipe.forEach(i ->
                stringBuilder.append(String.format("%s %s %s %-3s", i.getNumberOfShots(), "x [", i.getIngredient().getIngredientName(), "]")));
    }
}