package org.fantasticcoffee.shop.service;

import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.CoffeeType;

public interface ICoffee {

    Double getCoffeePrice(Coffee coffee);

    Double getCoffeeCost(Coffee coffee);

    Double getCoffeeTypePrice(CoffeeType coffeeType);
}
