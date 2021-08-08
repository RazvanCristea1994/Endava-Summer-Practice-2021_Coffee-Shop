package org.fantasticcoffee.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.fantasticcoffee.shop.model.JoinClasses.CoffeeIngredient;
import org.fantasticcoffee.shop.model.JoinClasses.StandardRecipeIngredient;

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
@Entity
@Table(name = "ingredients")
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int numberOfShots;
    @Column(nullable = false)
    private Double ingredientSellingPrice;
    @Column(nullable = false)
    private Double ingredientCost;
    @Column(nullable = false)
    private Double quantityPerShot;
    @Column(nullable = false)
    private String unitOfMeasurement;

    @OneToMany(mappedBy = "ingredient")
    private List<StandardRecipeIngredient> standardRecipe;

    @OneToMany(mappedBy = "ingredient")
    private List<CoffeeIngredient> coffeeIngredient;

    @Setter
    public static class Builder {

        private String name;
        private int numberOfShots;

        public Builder(String name, int numberOfShots) {
            this.name = name;
            this.numberOfShots = numberOfShots;
        }

        public Ingredient build() {

            Ingredient ingredient = new Ingredient();
            ingredient.name = this.name;
            ingredient.numberOfShots = this.numberOfShots;

            return ingredient;
        }
    }
}