package org.fantasticcoffee.shop.model.ingredient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientInStock {

    private Integer id;
    private Ingredient ingredient;
    private int quantity;

    @Setter
    public static class Builder {

        private Integer id;
        private Ingredient ingredient;
        private int quantity;

        public Builder(Integer id, Ingredient ingredient, int quantity) {
            this.id = id;
            this.ingredient = ingredient;
            this.quantity = quantity;
        }

        public Builder(Ingredient ingredient, int quantity) {
            this.ingredient = ingredient;
            this.quantity = quantity;
        }

        public IngredientInStock build() {

            IngredientInStock ingredientInStock = new IngredientInStock();
            ingredientInStock.id = this.id;
            ingredientInStock.ingredient = this.ingredient;
            ingredientInStock.quantity = this.quantity;

            return ingredientInStock;
        }
    }
}