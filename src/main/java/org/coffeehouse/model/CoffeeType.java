package org.coffeehouse.model;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;

public enum CoffeeType {

    ESPRESSO {
        @Override
        public Collection<Ingredients> getRecipe() {
            Collection<Ingredients> ingredients = new ArrayList<>();
            ingredients.addAll(asList(
                    Ingredients.ESPRESSO_SHOT,
                    Ingredients.ESPRESSO_SHOT));

            return ingredients;
        }
    },

    MACHIATTO {
        @Override
        public Collection<Ingredients> getRecipe() {

            Collection<Ingredients> ingredients = new ArrayList<>();
            ingredients.addAll(asList(
                    Ingredients.ESPRESSO_SHOT,
                    Ingredients.ESPRESSO_SHOT,
                    Ingredients.MILK_FOAM));

            return ingredients;
        }
    },

    CAFFEE_LATTE {
        @Override
        public Collection<Ingredients> getRecipe() {

            Collection<Ingredients> ingredients = new ArrayList<>();
            ingredients.addAll(asList(
                    Ingredients.ESPRESSO_SHOT,
                    Ingredients.STEAMED_MILK,
                    Ingredients.STEAMED_MILK,
                    Ingredients.MILK_FOAM));

            return ingredients;
        }
    },

    CAPPUCCINO {
        @Override
        public Collection<Ingredients> getRecipe() {

            Collection<Ingredients> ingredients = new ArrayList<>();
            ingredients.addAll(asList(
                    Ingredients.ESPRESSO_SHOT,
                    Ingredients.STEAMED_MILK,
                    Ingredients.MILK_FOAM,
                    Ingredients.MILK_FOAM));

            return ingredients;
        }
    },

    CAFFEE_MIEL {
        @Override
        public Collection<Ingredients> getRecipe() {

            Collection<Ingredients> ingredients = new ArrayList<>();
            ingredients.addAll(asList(
                    Ingredients.BLACK_COFFEE,
                    Ingredients.BLACK_COFFEE,
                    Ingredients.HONEY,
                    Ingredients.CINNAMON,
                    Ingredients.STEAMED_MILK));

            return ingredients;
        }
    };

    public abstract Collection<Ingredients> getRecipe();
}