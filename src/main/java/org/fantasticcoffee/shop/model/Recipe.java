package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.model.ingredientonrecipe.BaseIngredientOnRecipe;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class Recipe {

    @NotEmpty(message = "Base Ingredients are required")
    private List<BaseIngredientOnRecipe> baseIngredients = new ArrayList<>();

    private List<ExtraIngredientOnRecipe> extraIngredients = new ArrayList<>();

    public Recipe(Builder builder) {
        this.baseIngredients = builder.baseIngredientsConfig;
        this.extraIngredients = builder.extraIngredientConfig;
    }

    public void addExtraIngredient(ExtraIngredientOnRecipe ingredient) {
        this.extraIngredients.add(ingredient);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Builder {

        private List<BaseIngredientOnRecipe> baseIngredientsConfig;
        private List<ExtraIngredientOnRecipe> extraIngredientConfig;

        public Builder(List<BaseIngredientOnRecipe> baseIngredients, List<ExtraIngredientOnRecipe> extraIngredients) {
            this.baseIngredientsConfig = baseIngredients;
            this.extraIngredientConfig = extraIngredients;
        }

        public Builder(List<BaseIngredientOnRecipe> baseIngredientsConfig) {
            this.baseIngredientsConfig = baseIngredientsConfig;
        }

        public Recipe build() {
            return new Recipe(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (!baseIngredients.isEmpty() && !extraIngredients.isEmpty()) {
            getBaseIngredients(stringBuilder);
            getExtraIngredients(stringBuilder);
        } else if (!extraIngredients.isEmpty()) {
            getExtraIngredients(stringBuilder);
        } else if (!baseIngredients.isEmpty()) {
            getBaseIngredients(stringBuilder);
        }
        return String.valueOf(stringBuilder);
    }

    private void getBaseIngredients(StringBuilder stringBuilder) {
        baseIngredients.forEach(i ->
                stringBuilder.append(String.format("%s %s %s %-3s", i.getQuantity(), "x [", i.getBaseIngredient().getIngredientName(), "]")));
    }

    private void getExtraIngredients(StringBuilder stringBuilder) {
        extraIngredients.forEach(e ->
                stringBuilder.append(String.format("%s %1s %s %-3s", e.getQuantity(), "x [", e.getExtraIngredient().getIngredientName(), "]")));
    }
}