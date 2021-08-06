package org.fantasticcoffee.shop.data.order;

import lombok.Getter;
import org.fantasticcoffee.shop.data.card.CardRequest;
import org.fantasticcoffee.shop.data.customcoffee.CoffeeRequest;
import org.fantasticcoffee.shop.model.WhereToDrink;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
public class OrderRequest {

    @NotNull(message = "Customer name requested")
    @NotBlank(message = "Customer name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z '-]+$", message = "Wrong name format")
    private String customerName;

    @Valid
    private final List<CoffeeRequest> coffeeList;

    @Valid
    @NotNull(message = "Requested")
    private final WhereToDrink whereToDrink;

    @Valid
    @NotNull(message = "Credit card details are requested")
    private final CardRequest cardRequest;

    public OrderRequest(String customerName,
                        List<CoffeeRequest> coffeeList,
                        WhereToDrink whereToDrink,
                        CardRequest cardRequest) {
        this.customerName = customerName;
        this.coffeeList = coffeeList;
        this.whereToDrink = whereToDrink;
        this.cardRequest = cardRequest;
    }
}