package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.ingredient.Ingredient;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;
import org.fantasticcoffee.shop.repository.DefaultIngredientRepository;
import org.fantasticcoffee.shop.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("ingredientService")
public class DefaultIngredientService implements IngredientService {

    private static Integer idExtraIngredient = 0;
    private static Integer idBaseIngredient = 0;

    @Autowired
    DefaultIngredientRepository ingredientRepository;

    @PostConstruct
    public void seedStock() {

        this.ingredientRepository.save(new IngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, Ingredient.MILK, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, Ingredient.HONEY, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, Ingredient.SYRUP, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, Ingredient.STEAMED_MILK, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, Ingredient.MILK_FOAM, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, Ingredient.SWEETENED_CONDENSED_MILK, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, Ingredient.ICE_CREAM, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, Ingredient.WHIPPED_CREAM, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, Ingredient.CINNAMON, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, Ingredient.HOT_WATER, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, Ingredient.ICE_CUBES, 20).build());

        this.ingredientRepository.save(new IngredientInStock.Builder(++DefaultIngredientService.idBaseIngredient, Ingredient.ESPRESSO_SHOT, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(++DefaultIngredientService.idBaseIngredient, Ingredient.BLACK_COFFEE, 20).build());
    }

    public List<IngredientInStock> getAllIngredientsInStock() {
        return this.ingredientRepository.findAll();
    }

    @Override
    public void decrementIngredient(List<IngredientOnRecipe> ingredientToChange) {

        ingredientToChange.forEach(baseIngredientOnRecipe -> {
            IngredientInStock ingredient = this.ingredientRepository.find(baseIngredientOnRecipe.getIngredient());
            ingredient.setQuantity(ingredient.getQuantity() - baseIngredientOnRecipe.getQuantity());
        });
    }

    @Override
    public Double getPriceForShots(IngredientOnRecipe ingredient) {
        return ingredient.getQuantity() * ingredient.getIngredient().getIngredientSellingPrice();
    }
}