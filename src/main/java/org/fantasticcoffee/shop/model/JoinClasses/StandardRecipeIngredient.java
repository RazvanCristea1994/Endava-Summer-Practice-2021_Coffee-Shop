package org.fantasticcoffee.shop.model.JoinClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fantasticcoffee.shop.model.Ingredient;
import org.fantasticcoffee.shop.model.StandardRecipe;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "standard_recipe_ingredient")
@NoArgsConstructor
@AllArgsConstructor
public class StandardRecipeIngredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "standard_recipe_id")
    private StandardRecipe standardRecipe;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(nullable = false)
    private int numberOfShots;
}