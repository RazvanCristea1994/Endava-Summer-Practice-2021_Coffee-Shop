package org.fantasticcoffee.shop.model.stock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.model.ingredientdefinition.ExtraIngredient;

@Getter
@Setter
@NoArgsConstructor
public class ExtraIngredientInStock {

    private Integer id;
    private ExtraIngredient extraIngredient;
    private int quantity;

    @Setter
    public static class Builder {

        private Integer id;
        private ExtraIngredient extraIngredient;
        private int quantity;

        public Builder(Integer id, ExtraIngredient extraIngredient, int quantity) {
            this.id = id;
            this.extraIngredient = extraIngredient;
            this.quantity = quantity;
        }

        public Builder(ExtraIngredient extraIngredient, int quantity) {
            this.extraIngredient = extraIngredient;
            this.quantity = quantity;
        }

        public ExtraIngredientInStock build() {

            ExtraIngredientInStock extraIngredientInStock = new ExtraIngredientInStock();
            extraIngredientInStock.id = this.id;
            extraIngredientInStock.extraIngredient = this.extraIngredient;
            extraIngredientInStock.quantity = this.quantity;

            return extraIngredientInStock;
        }
    }
}