package org.fantasticcoffee.shop.data.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeRequest;
import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeRequest;
import org.fantasticcoffee.shop.model.Card;
import org.fantasticcoffee.shop.model.WhereToDrink;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {

    private List<CustomCoffeeRequest> customCoffeeList;
    private List<CustomizableStandardCoffeeRequest> customizableStandardCoffee;
    private WhereToDrink whereToDrink;
    private Card card;
}