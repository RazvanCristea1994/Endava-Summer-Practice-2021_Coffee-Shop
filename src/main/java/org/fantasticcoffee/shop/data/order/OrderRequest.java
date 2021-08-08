package org.fantasticcoffee.shop.data.order;

import lombok.Getter;
import org.fantasticcoffee.shop.data.card.CardRequest;
import org.fantasticcoffee.shop.data.customcoffee.CoffeeRequest;
import org.fantasticcoffee.shop.model.OrderType;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
public class OrderRequest {

    @NotNull(message = "Customer name requested ")
    @NotBlank(message = "Customer name cannot be blank ")
    @Pattern(regexp = "^[a-zA-Z '-]+$", message = "Wrong name format ")
    private final String customerName;

    @Valid
    @NotNull(message = "Coffee list cannot be null")
    private final List<CoffeeRequest> coffeeList;

    @Valid
    @NotNull(message = "Order type requested ")
    private final OrderType orderType;

    @Valid
    @NotNull(message = "Credit card details are requested ")
    private final CardRequest cardRequest;

    public OrderRequest(String customerName,
                        List<CoffeeRequest> coffeeList,
                        OrderType orderType,
                        CardRequest cardRequest) {
        this.customerName = customerName;
        this.coffeeList = coffeeList;
        this.orderType = orderType;
        this.cardRequest = cardRequest;
    }
}