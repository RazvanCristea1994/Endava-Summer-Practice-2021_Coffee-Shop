package org.fantasticcoffee.shop.model;

import lombok.Getter;
import org.fantasticcoffee.shop.model.ingredientdefinition.BaseIngredientDefinition;
import org.fantasticcoffee.shop.model.ingredientdefinition.ExtraIngredientDefinition;
import org.fantasticcoffee.shop.model.ingredientonrecipe.BaseIngredientOnRecipe;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;

import java.util.Arrays;

@Getter
public enum CoffeeType {

    ESPRESSO("Espresso", new CoffeeRecipe.Builder(
            Arrays.asList(new BaseIngredientOnRecipe(BaseIngredientDefinition.ESPRESSO_SHOT, 2)),
            Arrays.asList()).build()),

    MACHIATTO("Machiatto", new CoffeeRecipe.Builder(
            Arrays.asList(new BaseIngredientOnRecipe(BaseIngredientDefinition.ESPRESSO_SHOT, 2)),
            Arrays.asList(new ExtraIngredientOnRecipe(ExtraIngredientDefinition.MILK_FOAM, 1))).build()),

    CAFFEE_LATTE("Caffee Latte", new CoffeeRecipe.Builder(
            Arrays.asList(new BaseIngredientOnRecipe(BaseIngredientDefinition.ESPRESSO_SHOT, 2)),
            Arrays.asList(new ExtraIngredientOnRecipe(ExtraIngredientDefinition.STEAMED_MILK, 2),
                    new ExtraIngredientOnRecipe(ExtraIngredientDefinition.MILK_FOAM, 1))).build()),

    CAPPUCCINO("Cappuccino", new CoffeeRecipe.Builder(
            Arrays.asList(new BaseIngredientOnRecipe(BaseIngredientDefinition.ESPRESSO_SHOT, 1)),
            Arrays.asList(new ExtraIngredientOnRecipe(ExtraIngredientDefinition.STEAMED_MILK, 1),
                    new ExtraIngredientOnRecipe(ExtraIngredientDefinition.MILK_FOAM, 2))).build()),

    CAFFEE_MIEL("Caffee Miel", new CoffeeRecipe.Builder(
            Arrays.asList(new BaseIngredientOnRecipe(BaseIngredientDefinition.BLACK_COFFEE, 2)),
            Arrays.asList(new ExtraIngredientOnRecipe(ExtraIngredientDefinition.HONEY, 1),
                    new ExtraIngredientOnRecipe(ExtraIngredientDefinition.CINNAMON, 1),
                    new ExtraIngredientOnRecipe(ExtraIngredientDefinition.STEAMED_MILK, 1))).build()),

    DEFAULT("Be Creative!",
            new CoffeeRecipe());

    private String name;
    private CoffeeRecipe recipe;

    CoffeeType(String name, CoffeeRecipe recipe) {
        this.name = name;
        this.recipe = recipe;
    }
}