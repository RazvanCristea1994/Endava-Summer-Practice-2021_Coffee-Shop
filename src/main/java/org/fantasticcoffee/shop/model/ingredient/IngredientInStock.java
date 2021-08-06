package org.fantasticcoffee.shop.model.ingredient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.fantasticcoffee.shop.model.StandardRecipeIngredientInStock;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

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

    @Column
    private String name;
    @Column
    private int numberOfShots;
    @Column
    private Double ingredientSellingPrice;
    @Column
    private Double ingredientCost;
    @Column
    private Double quantityPerShot;
    @Column
    private String unitOfMeasurement;

    @OneToMany(mappedBy = "ingredientInStock")
    private List<StandardRecipeIngredientInStock> standardRecipeInStock;

    @Setter
    public static class Builder {

        private String name;
        private int numberOfShots;

        public Builder(String name, int numberOfShots) {
            this.name = name;
            this.numberOfShots = numberOfShots;
        }

        public IngredientInStock build() {

            IngredientInStock ingredientInStock = new IngredientInStock();
            ingredientInStock.name = this.name;
            ingredientInStock.numberOfShots = this.numberOfShots;

            return ingredientInStock;
        }
    }
}