package org.fantasticcoffee.shop.model.JoinClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.Ingredient;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeIngredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coffee_id")
    private Coffee coffee;

    @Column(nullable = false)
    private int numberOfShots;

    public CoffeeIngredient(Ingredient ingredient, int numberOfShots) {
        this.ingredient = ingredient;
        this.numberOfShots = numberOfShots;
    }
}