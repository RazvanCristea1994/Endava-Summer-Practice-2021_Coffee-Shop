package org.fantasticcoffee.shop.model.stock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.model.ingredientdefinition.BaseIngredient;

@Getter
@Setter
@NoArgsConstructor
public class BaseIngredientInStock {

    private Integer id;
    private BaseIngredient baseIngredient;
    private int quantity;

    @Setter
    public static class Builder {

        private Integer id;
        private BaseIngredient baseIngredient;
        private int quantity;

        public Builder(Integer id, BaseIngredient baseIngredient, int quantity) {
            this.id = id;
            this.baseIngredient = baseIngredient;
            this.quantity = quantity;
        }

        public Builder(BaseIngredient baseIngredient, int quantity) {
            this.baseIngredient = baseIngredient;
            this.quantity = quantity;
        }

        public BaseIngredientInStock build() {

            BaseIngredientInStock baseIngredientInStock = new BaseIngredientInStock();
            baseIngredientInStock.id = this.id;
            baseIngredientInStock.baseIngredient = this.baseIngredient;
            baseIngredientInStock.quantity = this.quantity;

            return baseIngredientInStock;
        }
    }
}