package org.fantasticcoffee.shop.model.coffee;

import lombok.Getter;
import org.fantasticcoffee.shop.model.Recipe;
import org.fantasticcoffee.shop.model.ingredient.Ingredient;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;

import java.util.Arrays;

@Getter
public enum StandardCoffee {

    ESPRESSO("Espresso", new Recipe.Builder(Arrays.asList(
            new IngredientOnRecipe(Ingredient.ESPRESSO_SHOT, 2))).build()),

    MACHIATTO("Machiatto", new Recipe.Builder(Arrays.asList(
            new IngredientOnRecipe(Ingredient.ESPRESSO_SHOT, 2),
            new IngredientOnRecipe(Ingredient.MILK_FOAM, 1))).build()),

    CAFFEE_LATTE("Caffee Latte", new Recipe.Builder(Arrays.asList(
            new IngredientOnRecipe(Ingredient.ESPRESSO_SHOT, 2),
            new IngredientOnRecipe(Ingredient.STEAMED_MILK, 2),
            new IngredientOnRecipe(Ingredient.MILK_FOAM, 1))).build()),

    CAPPUCCINO("Cappuccino", new Recipe.Builder(Arrays.asList(
            new IngredientOnRecipe(Ingredient.ESPRESSO_SHOT, 1),
            new IngredientOnRecipe(Ingredient.STEAMED_MILK, 1),
            new IngredientOnRecipe(Ingredient.MILK_FOAM, 2))).build()),

    CAFFEE_MIEL("Caffee Miel", new Recipe.Builder(Arrays.asList(
            new IngredientOnRecipe(Ingredient.BLACK_COFFEE, 2),
            new IngredientOnRecipe(Ingredient.HONEY, 1),
            new IngredientOnRecipe(Ingredient.CINNAMON, 1),
            new IngredientOnRecipe(Ingredient.STEAMED_MILK, 1))).build());

    private String name;
    private Recipe recipe;

    StandardCoffee(String name, Recipe recipe) {
        this.name = name;
        this.recipe = recipe;
    }
}