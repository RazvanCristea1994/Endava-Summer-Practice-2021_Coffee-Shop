package org.fantasticcoffee.shop.model;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;

public enum CoffeeType {

    ESPRESSO("Espresso") {
        @Override
        public Collection<Ingredient> getRecipe() {
            Collection<Ingredient> ingredients = new ArrayList<>();
            ingredients.addAll(asList(
                    Ingredient.ESPRESSO_SHOT,
                    Ingredient.ESPRESSO_SHOT));

            return ingredients;
        }
    },

    MACHIATTO("Machiatto") {
        @Override
        public Collection<Ingredient> getRecipe() {

            Collection<Ingredient> ingredients = new ArrayList<>();
            ingredients.addAll(asList(
                    Ingredient.ESPRESSO_SHOT,
                    Ingredient.ESPRESSO_SHOT,
                    Ingredient.MILK_FOAM));

            return ingredients;
        }
    },

    CAFFEE_LATTE("Caffee Latte") {
        @Override
        public Collection<Ingredient> getRecipe() {

            Collection<Ingredient> ingredients = new ArrayList<>();
            ingredients.addAll(asList(
                    Ingredient.ESPRESSO_SHOT,
                    Ingredient.STEAMED_MILK,
                    Ingredient.STEAMED_MILK,
                    Ingredient.MILK_FOAM));

            return ingredients;
        }
    },

    CAPPUCCINO("Cappuccino") {
        @Override
        public Collection<Ingredient> getRecipe() {

            Collection<Ingredient> ingredients = new ArrayList<>();
            ingredients.addAll(asList(
                    Ingredient.ESPRESSO_SHOT,
                    Ingredient.STEAMED_MILK,
                    Ingredient.MILK_FOAM,
                    Ingredient.MILK_FOAM));

            return ingredients;
        }
    },

    CAFFEE_MIEL("Caffee Miel") {
        @Override
        public Collection<Ingredient> getRecipe() {

            Collection<Ingredient> ingredients = new ArrayList<>();
            ingredients.addAll(asList(
                    Ingredient.BLACK_COFFEE,
                    Ingredient.BLACK_COFFEE,
                    Ingredient.HONEY,
                    Ingredient.CINNAMON,
                    Ingredient.STEAMED_MILK));

            return ingredients;
        }
    },

    DEFAULT("My Creation") {
        @Override
        public Collection<Ingredient> getRecipe() {
            return new ArrayList<>();
        }
    };

    private String name;

    public abstract Collection<Ingredient> getRecipe();

    public Double getPrice() {
        return getRecipe().stream().mapToDouble(Ingredient::getIngredientSellingPrice).sum();
    }

    CoffeeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}