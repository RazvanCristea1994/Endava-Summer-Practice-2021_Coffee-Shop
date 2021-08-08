package org.fantasticcoffee.shop.validator;

import org.fantasticcoffee.shop.model.JoinClasses.CoffeeIngredient;
import org.fantasticcoffee.shop.model.Order;
import org.springframework.stereotype.Component;

@Component
public class CoffeeValidator {

    public static void coffeeCheck(Order order) {

        order.getCoffeeList().forEach(coffee -> {

            if (coffee.getChosenIngredients().isEmpty()) {
                throw new IllegalArgumentException("Error. At least one coffee was empty. ");
            }

            if (!coffee.getChosenIngredients().isEmpty()) {
                int coffeeShotsCount = 0;
                for (CoffeeIngredient ingredient: coffee.getChosenIngredients()) {
                    if ("ESPRESSO_SHOT".equalsIgnoreCase(ingredient.getIngredient().getName()) ||
                            "BLACK_COFFEE".equalsIgnoreCase(ingredient.getIngredient().getName())){
                        coffeeShotsCount++;
                    }
                }

                if (coffeeShotsCount == 0) {
                    throw new IllegalArgumentException("Error. You cannot buy only extra ingredients. " +
                            "Please choose one of our coffee shots ");
                }
            }
        });
    }
}
