package org.fantasticcoffee.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "standard_recipe")
@NoArgsConstructor
@AllArgsConstructor
public class StandardRecipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "standard_recipe", unique = true)
    private String name;

    @OneToMany(mappedBy = "standardRecipe")
    private List<StandardRecipeIngredient> ingredientList;

    public StandardRecipe(String name) {
        this.name = name;
    }
}