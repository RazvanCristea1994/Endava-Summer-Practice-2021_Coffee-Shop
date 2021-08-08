package org.fantasticcoffee.shop.validator;

import org.apache.log4j.Logger;
import org.fantasticcoffee.shop.model.JoinClasses.CoffeeIngredient;
import org.fantasticcoffee.shop.model.Order;
import org.springframework.stereotype.Component;

@Component
public class CoffeeValidator {

    private final static Logger log = Logger.getLogger(CoffeeValidator.class.getName());

    public static void coffeeCheck(Order order) {

        order.getCoffeeList().forEach(coffee -> {

            if (coffee.getChosenIngredients().isEmpty()) {
                log.error("Error. At least one coffee was empty");
                throw new IllegalArgumentException("Error. At least one coffee was empty. ");
            }

            if (!coffee.getChosenIngredients().isEmpty()) {
                int coffeeShotsCount = 0;
                for (CoffeeIngredient ingredient : coffee.getChosenIngredients()) {
                    if ("ESPRESSO_SHOT".equalsIgnoreCase(ingredient.getIngredient().getName()) ||
                            "BLACK_COFFEE".equalsIgnoreCase(ingredient.getIngredient().getName())) {
                        coffeeShotsCount++;
                    }
                }

                if (coffeeShotsCount == 0) {
                    log.error("The coffee contained only extra ingredients");
                    throw new IllegalArgumentException("Error. You cannot buy only extra ingredients. " +
                            "Please choose one of our coffee shots as well.");
                }
            }
        });
    }
}