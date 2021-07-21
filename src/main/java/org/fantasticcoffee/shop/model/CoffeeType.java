package org.fantasticcoffee.shop.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Arrays.asList;

@Getter
public enum CoffeeType {

    ESPRESSO("Espresso",
            CoffeeRecipe.withBaseIngredients(
                    new ArrayList<>(asList(
                            BaseIngredient.ESPRESSO_SHOT,
                            BaseIngredient.ESPRESSO_SHOT)))),

    MACHIATTO("Machiatto",
            new CoffeeRecipe(
                    new ArrayList<>(asList(
                            BaseIngredient.ESPRESSO_SHOT,
                            BaseIngredient.ESPRESSO_SHOT)), Collections.singletonList(ExtraIngredient.MILK_FOAM))),

    CAFFEE_LATTE("Caffee Latte",
            new CoffeeRecipe(
                    new ArrayList<>(asList(
                            BaseIngredient.ESPRESSO_SHOT,
                            BaseIngredient.ESPRESSO_SHOT)),
                    new ArrayList<>(asList(ExtraIngredient.STEAMED_MILK,
                            ExtraIngredient.STEAMED_MILK,
                            ExtraIngredient.MILK_FOAM)))),

    CAPPUCCINO("Cappuccino",
            new CoffeeRecipe(
                    new ArrayList<>(Collections.singletonList(
                            BaseIngredient.ESPRESSO_SHOT)),
                    new ArrayList<>(asList(ExtraIngredient.STEAMED_MILK,
                            ExtraIngredient.MILK_FOAM,
                            ExtraIngredient.MILK_FOAM)))),

    CAFFEE_MIEL("Caffee Miel",
            new CoffeeRecipe(
                    new ArrayList<>(asList(
                            BaseIngredient.BLACK_COFFEE,
                            BaseIngredient.BLACK_COFFEE)),
                    new ArrayList<>(asList(ExtraIngredient.HONEY,
                            ExtraIngredient.CINNAMON,
                            ExtraIngredient.STEAMED_MILK)))),

    DEFAULT("Be Creative!",
            new CoffeeRecipe());

    private String name;
    private CoffeeRecipe recipe;

    CoffeeType(String name, CoffeeRecipe recipe) {
        this.name = name;
        this.recipe = recipe;
    }
}