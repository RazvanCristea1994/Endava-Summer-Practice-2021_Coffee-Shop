package org.fantasticcoffee.shop.service;

import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.fantasticcoffee.shop.model.coffee.StandardCoffee;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;

public interface CoffeeService {

    Double getCoffeePrice(CustomCoffee customCoffee);


    Double getCoffeePrice(CustomizableStandardCoffee customCoffee);


    Double getCoffeeCost(CustomCoffee customCoffee);


    Double getCoffeeCost(CustomizableStandardCoffee customCoffee);


    Double getStandardCoffeePrice(StandardCoffee standardCoffee);
}