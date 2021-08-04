package org.fantasticcoffee.shop.model.ingredient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ingredients_in_stock")
public class IngredientInStock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ingredient", unique = true)
    private Ingredient ingredient;

    @Column
    private int numberOfShots;

    @Setter
    public static class Builder {

        private Ingredient ingredient;
        private int numberOfShots;

        public Builder(Ingredient ingredient, int numberOfShots) {
            this.ingredient = ingredient;
            this.numberOfShots = numberOfShots;
        }

        public IngredientInStock build() {

            IngredientInStock ingredientInStock = new IngredientInStock();
            ingredientInStock.ingredient = this.ingredient;
            ingredientInStock.numberOfShots = this.numberOfShots;

            return ingredientInStock;
        }
    }
}