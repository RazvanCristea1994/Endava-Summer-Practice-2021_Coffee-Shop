package org.fantasticcoffee.shop.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

@Getter
public enum CoffeeType {

    ESPRESSO("Espresso") {
        @Override
        public CoffeeRecipe getRecipe() {

            List<BaseIngredient> baseIngredients = new ArrayList<>(asList(
                    BaseIngredient.ESPRESSO_SHOT,
                    BaseIngredient.ESPRESSO_SHOT));

            return CoffeeRecipe.withBaseIngredients(baseIngredients);
        }
    },

    MACHIATTO("Machiatto") {
        @Override
        public CoffeeRecipe getRecipe() {

            List<BaseIngredient> baseIngredients = new ArrayList<>(asList(
                    BaseIngredient.ESPRESSO_SHOT,
                    BaseIngredient.ESPRESSO_SHOT));

            List<ExtraIngredient> extraIngredients = new ArrayList<>();
            extraIngredients.addAll(Collections.singletonList(ExtraIngredient.MILK_FOAM));

            return new CoffeeRecipe(baseIngredients, extraIngredients);
        }
    },

    CAFFEE_LATTE("Caffee Latte") {
        @Override
        public CoffeeRecipe getRecipe() {

            List<BaseIngredient> baseIngredients = new ArrayList<>();
            baseIngredients.addAll(asList(
                    BaseIngredient.ESPRESSO_SHOT));

            List<ExtraIngredient> extraIngredients = new ArrayList<>();
            extraIngredients.addAll(asList(ExtraIngredient.STEAMED_MILK,
                    ExtraIngredient.STEAMED_MILK,
                    ExtraIngredient.MILK_FOAM));

            return new CoffeeRecipe(baseIngredients, extraIngredients);
        }
    },

    CAPPUCCINO("Cappuccino") {
        @Override
        public CoffeeRecipe getRecipe() {

            List<BaseIngredient> baseIngredients = new ArrayList<>();
            baseIngredients.addAll(Collections.singletonList(
                    BaseIngredient.ESPRESSO_SHOT));

            List<ExtraIngredient> extraIngredients = new ArrayList<>();
            extraIngredients.addAll(asList(ExtraIngredient.STEAMED_MILK,
                    ExtraIngredient.MILK_FOAM,
                    ExtraIngredient.MILK_FOAM));

            return new CoffeeRecipe(baseIngredients, extraIngredients);
        }
    },

    CAFFEE_MIEL("Caffee Miel") {
        @Override
        public CoffeeRecipe getRecipe() {

            List<BaseIngredient> baseIngredients = new ArrayList<>();
            baseIngredients.addAll(asList(
                    BaseIngredient.BLACK_COFFEE,
                    BaseIngredient.BLACK_COFFEE));

            List<ExtraIngredient> extraIngredients = new ArrayList<>();
            extraIngredients.addAll(asList(ExtraIngredient.HONEY,
                    ExtraIngredient.CINNAMON,
                    ExtraIngredient.STEAMED_MILK));

            return new CoffeeRecipe(baseIngredients, extraIngredients);
        }
    },

    DEFAULT("Be Creative!") {
        @Override
        public CoffeeRecipe getRecipe() {
            return new CoffeeRecipe();
        }
    };

    private String name;

    public abstract CoffeeRecipe getRecipe();

    CoffeeType(String name) {
        this.name = name;
    }
}