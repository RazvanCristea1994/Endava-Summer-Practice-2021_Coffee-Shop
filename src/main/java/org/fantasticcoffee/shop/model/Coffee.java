package org.fantasticcoffee.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.model.JoinClasses.CoffeeIngredient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Coffee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String coffeeName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "standard_recipe_id", nullable = false)
    private StandardRecipe standardRecipe;

    @OneToMany(mappedBy = "coffee", cascade = CascadeType.REMOVE)
    private List<CoffeeIngredient> chosenIngredients;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public Coffee(String coffeeName,
                  StandardRecipe standardRecipe,
                  List<CoffeeIngredient> chosenIngredients,
                  double price) {
        this.coffeeName = coffeeName;
        this.standardRecipe = standardRecipe;
        this.chosenIngredients = chosenIngredients;
        this.price = price;
    }

    public Coffee(String coffeeName,
                  StandardRecipe standardRecipe,
                  List<CoffeeIngredient> chosenIngredients) {
        this.coffeeName = coffeeName;
        this.standardRecipe = standardRecipe;
        this.chosenIngredients = chosenIngredients;
    }
}