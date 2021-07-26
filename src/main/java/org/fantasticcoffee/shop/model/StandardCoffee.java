package org.fantasticcoffee.shop.model;

import lombok.Getter;
import org.fantasticcoffee.shop.model.ingredientdefinition.BaseIngredient;
import org.fantasticcoffee.shop.model.ingredientdefinition.ExtraIngredient;
import org.fantasticcoffee.shop.model.ingredientonrecipe.BaseIngredientOnRecipe;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;

import java.util.Arrays;

@Getter
public enum StandardCoffee {

    ESPRESSO("Espresso", new Recipe.Builder(
            Arrays.asList(new BaseIngredientOnRecipe(BaseIngredient.ESPRESSO_SHOT, 2)),
            Arrays.asList()).build()),

    MACHIATTO("Machiatto", new Recipe.Builder(
            Arrays.asList(new BaseIngredientOnRecipe(BaseIngredient.ESPRESSO_SHOT, 2)),
            Arrays.asList(new ExtraIngredientOnRecipe(ExtraIngredient.MILK_FOAM, 1))).build()),

    CAFFEE_LATTE("Caffee Latte", new Recipe.Builder(
            Arrays.asList(new BaseIngredientOnRecipe(BaseIngredient.ESPRESSO_SHOT, 2)),
            Arrays.asList(new ExtraIngredientOnRecipe(ExtraIngredient.STEAMED_MILK, 2),
                    new ExtraIngredientOnRecipe(ExtraIngredient.MILK_FOAM, 1))).build()),

    CAPPUCCINO("Cappuccino", new Recipe.Builder(
            Arrays.asList(new BaseIngredientOnRecipe(BaseIngredient.ESPRESSO_SHOT, 1)),
            Arrays.asList(new ExtraIngredientOnRecipe(ExtraIngredient.STEAMED_MILK, 1),
                    new ExtraIngredientOnRecipe(ExtraIngredient.MILK_FOAM, 2))).build()),

    CAFFEE_MIEL("Caffee Miel", new Recipe.Builder(
            Arrays.asList(new BaseIngredientOnRecipe(BaseIngredient.BLACK_COFFEE, 2)),
            Arrays.asList(new ExtraIngredientOnRecipe(ExtraIngredient.HONEY, 1),
                    new ExtraIngredientOnRecipe(ExtraIngredient.CINNAMON, 1),
                    new ExtraIngredientOnRecipe(ExtraIngredient.STEAMED_MILK, 1))).build());

    private String name;
    private Recipe recipe;

    StandardCoffee(String name, Recipe recipe) {
        this.name = name;
        this.recipe = recipe;
    }
}