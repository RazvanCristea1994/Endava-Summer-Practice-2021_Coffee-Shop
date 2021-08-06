package org.fantasticcoffee.shop.model;

import lombok.Getter;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;
import org.fantasticcoffee.shop.model.recipe.StandardRecipeInStock;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "standard_recipe_ingredient_in_stock")
public class StandardRecipeIngredientInStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_standard_recipe_in_stock")
    StandardRecipeInStock standardRecipeInStock;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ingredient_in_stock")
    IngredientInStock ingredientInStock;

    @Column(name = "number_of_shots")
    int numberOfShots;
}