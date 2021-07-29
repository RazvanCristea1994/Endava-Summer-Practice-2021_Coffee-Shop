package org.fantasticcoffee.shop.data.order;

import lombok.Getter;
import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeRequest;
import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeRequest;
import org.fantasticcoffee.shop.model.Card;
import org.fantasticcoffee.shop.model.WhereToDrink;

import java.util.List;

@Getter
public class OrderRequest {

    private final List<CustomCoffeeRequest> customCoffeeList;
    private final List<CustomizableStandardCoffeeRequest> customizableStandardCoffee;
    private final WhereToDrink whereToDrink;
    private final Card card;

    public OrderRequest(List<CustomCoffeeRequest> customCoffeeList,
                        List<CustomizableStandardCoffeeRequest> customizableStandardCoffee,
                        WhereToDrink whereToDrink,
                        Card card) {
        this.customCoffeeList = customCoffeeList;
        this.customizableStandardCoffee = customizableStandardCoffee;
        this.whereToDrink = whereToDrink;
        this.card = card;
    }
}