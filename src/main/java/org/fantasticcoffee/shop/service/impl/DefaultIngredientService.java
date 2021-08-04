package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.ingredient.Ingredient;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;
import org.fantasticcoffee.shop.repository.database.IngredientInStockRepository;
import org.fantasticcoffee.shop.service.IngredientService;
import org.fantasticcoffee.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("ingredientService")
public class DefaultIngredientService implements IngredientService {

    @Autowired
    IngredientInStockRepository ingredientRepository;
    @Autowired
    OrderService orderService;

    @PostConstruct
    public void seedStock() {

     /*   this.ingredientRepository.save(new IngredientInStock.Builder(Ingredient.MILK, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(Ingredient.HONEY, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(Ingredient.SYRUP, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(Ingredient.STEAMED_MILK, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(Ingredient.MILK_FOAM, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(Ingredient.SWEETENED_CONDENSED_MILK, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(Ingredient.ICE_CREAM, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(Ingredient.WHIPPED_CREAM, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(Ingredient.CINNAMON, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(Ingredient.HOT_WATER, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(Ingredient.ICE_CUBES, 20).build());

        this.ingredientRepository.save(new IngredientInStock.Builder(Ingredient.ESPRESSO_SHOT, 20).build());
        this.ingredientRepository.save(new IngredientInStock.Builder(Ingredient.BLACK_COFFEE, 20).build());
   */
    }

    public Iterable<IngredientInStock> getAllIngredientsInStock() {
        return this.ingredientRepository.findAll();
    }

    @Override
    public void decrementIngredient(List<IngredientOnRecipe> ingredientToChange) {

        ingredientToChange.forEach(ingredientOnRecipe -> {
            IngredientInStock ingredient = this.ingredientRepository.findByIngredient(ingredientOnRecipe.getIngredient());
            ingredient.setNumberOfShots(ingredient.getNumberOfShots() - ingredientOnRecipe.getNumberOfShots());
            this.ingredientRepository.save(ingredient);
        });
    }

    @Override
    public Double getPriceForShots(IngredientOnRecipe ingredient) {
        return ingredient.getNumberOfShots() * ingredient.getIngredient().getIngredientSellingPrice();
    }

    @Override
    public List<IngredientOnRecipe> checkIngredientInStockForOrder(Order order) {

        List<IngredientOnRecipe> ingredientsOnRecipe = this.orderService.getAllIngredientsForOrder(order);
        Long nr = ingredientsOnRecipe
                .stream()
                .filter(ingredient -> ingredient.getNumberOfShots() > this.ingredientRepository.findByIngredient(ingredient.getIngredient()).getNumberOfShots())
                .count();

        if (nr > 0) {
            throw new IllegalArgumentException("We do not have enough ingredients for this order.");
        }
        return ingredientsOnRecipe;
    }
}