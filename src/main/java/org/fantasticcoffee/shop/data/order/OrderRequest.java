package org.fantasticcoffee.shop.data.order;

import lombok.Getter;
import org.fantasticcoffee.shop.data.card.CardRequest;
import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeRequest;
import org.fantasticcoffee.shop.data.customizablestandardcoffee.CoffeeWithStandardRecipeBaseRequest;
import org.fantasticcoffee.shop.model.WhereToDrink;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class OrderRequest {

    @Valid
    private final List<CustomCoffeeRequest> customCoffeeList;

    @Valid
    private final List<CoffeeWithStandardRecipeBaseRequest> coffeeWithStandardRecipeBase;

    @Valid
    @NotNull(message = "Requested")
    private final WhereToDrink whereToDrink;

    @Valid
    @NotNull(message = "Credit card details are requested")
    private final CardRequest card;

    public OrderRequest(List<CustomCoffeeRequest> customCoffeeList,
                        List<CoffeeWithStandardRecipeBaseRequest> coffeeWithStandardRecipeBase,
                        WhereToDrink whereToDrink,
                        CardRequest card) {

        this.customCoffeeList = customCoffeeList;
        this.coffeeWithStandardRecipeBase = coffeeWithStandardRecipeBase;
        this.whereToDrink = whereToDrink;
        this.card = card;
    }
}