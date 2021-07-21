package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CoffeeRecipe {

    private List<BaseIngredient> baseIngredients;
    private List<ExtraIngredient> extraIngredients;

    public CoffeeRecipe(List<BaseIngredient> baseIngredients, List<ExtraIngredient> extraIngredients) {
        this.baseIngredients = baseIngredients;
        this.extraIngredients = extraIngredients;
    }

    public static CoffeeRecipe withBaseIngredients(List<BaseIngredient> baseIngredients) {

        CoffeeRecipe coffeeRecipe = new CoffeeRecipe();
        coffeeRecipe.baseIngredients = baseIngredients;
        return coffeeRecipe;
    }

    public static CoffeeRecipe withExtraIngredients(List<ExtraIngredient> extraIngredients) {

        CoffeeRecipe coffeeRecipe = new CoffeeRecipe();
        coffeeRecipe.extraIngredients = extraIngredients;
        return coffeeRecipe;
    }

    @Override
    public String toString() {
        if (baseIngredients != null && extraIngredients != null) {
            return String.format("%s %s",baseIngredients.stream().map(BaseIngredient::getIngredientName)
                    .collect((Collectors.toList())), extraIngredients.stream()
                    .map(ExtraIngredient::getIngredientName)
                    .collect((Collectors.toList())));
        } else if (extraIngredients != null) {
            return String.format("%s",extraIngredients.stream()
                    .map(ExtraIngredient::getIngredientName)
                    .collect((Collectors.toList())));
        } else if (baseIngredients != null) {
            return String.format("%s",baseIngredients.stream().map(BaseIngredient::getIngredientName)
                    .collect((Collectors.toList())));
        }
        return null;
    }
}
