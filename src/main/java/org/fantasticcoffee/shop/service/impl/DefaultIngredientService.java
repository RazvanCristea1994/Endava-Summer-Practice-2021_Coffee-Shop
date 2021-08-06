package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.ingredient.ChosenIngredientIngredientInStock;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;
import org.fantasticcoffee.shop.repository.database.IngredientInStockRepository;
import org.fantasticcoffee.shop.service.IngredientService;
import org.fantasticcoffee.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service("ingredientService")
public class DefaultIngredientService implements IngredientService {

    @Autowired
    IngredientInStockRepository ingredientRepository;
    @Autowired
    OrderService orderService;

    public List<IngredientInStock> getAllIngredientsInStock() {

        Iterable<IngredientInStock> ingredientsInStock = this.ingredientRepository.findAll();
        List<IngredientInStock> ingredientListInStock = new ArrayList<>();
        ingredientsInStock.forEach(ingredientListInStock::add);

        return ingredientListInStock;
    }

    @Override
    public void decrementIngredient(Map<IngredientInStock, Integer> ingredientsToChange) {

        ingredientsToChange.forEach(
                (ingredientInStock, shotsNumber) -> {
                    IngredientInStock ingredient = this.ingredientRepository.findByName(ingredientInStock.getName());
                    ingredient.setNumberOfShots(ingredient.getNumberOfShots() - shotsNumber);
                    this.ingredientRepository.save(ingredient);
                });
    }

    @Override
    public Double getPriceForShots(ChosenIngredientIngredientInStock ingredient) {
        return ingredient.getNumberOfShots() * ingredient.getIngredient().getIngredientSellingPrice();
    }

    @Override
    public Map<IngredientInStock, Integer> checkIngredientInStockForOrder(Order order) {

        Map<IngredientInStock, Integer> allIngredientsForOrder = this.orderService.getAllIngredientsForOrder(order);
        StringBuilder message = new StringBuilder();

        allIngredientsForOrder.forEach(
                (ingredientInStock, shotsInOrder) -> {
                    if (shotsInOrder > this.ingredientRepository
                            .findByName(ingredientInStock.getName())
                            .getNumberOfShots()) {
                        message.append(ingredientInStock.getName() + " ");
                    }
                }
        );

        if (message.length() != 0) {
            throw new IllegalArgumentException("We do not have enough " + message + " in stock.");
        }

        return allIngredientsForOrder;
    }

    @Override
    public IngredientInStock getByName(String ingredientInStockName) {

        IngredientInStock ingredient = this.ingredientRepository.findByName(ingredientInStockName);
        if (ingredient == null) {
            throw new NoSuchElementException("We do not have the ingredient you are looking for");
        }
        return ingredient;
    }
}