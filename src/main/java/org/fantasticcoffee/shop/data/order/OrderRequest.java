package org.fantasticcoffee.shop.data.order;

import lombok.Getter;
import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeRequest;
import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeRequest;
import org.fantasticcoffee.shop.model.Card;
import org.fantasticcoffee.shop.model.WhereToDrink;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class OrderRequest {

    @Valid
    private final List<CustomCoffeeRequest> customCoffeeList;

    @Valid
    private final List<CustomizableStandardCoffeeRequest> customizableStandardCoffee;

    @Valid
    @NotNull(message = "Requested")
    private final WhereToDrink whereToDrink;

    @Valid
    @NotNull(message = "Credit card details are requested")
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