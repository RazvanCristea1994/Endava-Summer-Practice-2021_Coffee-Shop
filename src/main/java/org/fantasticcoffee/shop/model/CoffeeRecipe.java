package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.model.ingredientonrecipe.BaseIngredientOnRecipe;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CoffeeRecipe {

    private List<BaseIngredientOnRecipe> baseIngredients = new ArrayList<>();
    private List<ExtraIngredientOnRecipe> extraIngredients = new ArrayList<>();

    public CoffeeRecipe(Builder builder) {
        this.baseIngredients = builder.baseIngredientsConfig;
        this.extraIngredients = builder.extraIngredientConfig;
    }

    public void addExtraIngredient(ExtraIngredientOnRecipe ingredient) {
        this.extraIngredients.add(ingredient);
    }

    public static class Builder {

        private List<BaseIngredientOnRecipe> baseIngredientsConfig;
        private List<ExtraIngredientOnRecipe> extraIngredientConfig;

        public Builder(List<BaseIngredientOnRecipe> baseIngredients, List<ExtraIngredientOnRecipe> extraIngredients) {
            this.baseIngredientsConfig = baseIngredients;
            this.extraIngredientConfig = extraIngredients;
        }

        public Builder(List<BaseIngredientOnRecipe> baseIngredients) {
            this.baseIngredientsConfig = baseIngredients;
        }

        public CoffeeRecipe build() {
            return new CoffeeRecipe(this);
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