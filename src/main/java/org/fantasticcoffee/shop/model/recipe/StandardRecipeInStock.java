package org.fantasticcoffee.shop.model.recipe;

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
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "standard_recipe_in_stock")
public class StandardRecipeInStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "standard_recipe_id")
    private Integer id;

    @Column(name = "standard_recipe", unique = true)
    private String name;

    @OneToMany(mappedBy = "standardRecipeInStock")
    private List<StandardRecipeIngredientInStock> ingredientOnRecipeList;

    public StandardRecipeInStock(String name) {
        this.name = name;
    }
}