package org.fantasticcoffee.shop.data.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeResponse;
import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeResponse;
import org.fantasticcoffee.shop.model.WhereToDrink;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponse {

    private Integer id;
    private LocalDateTime orderDateTime;
    private List<CustomCoffeeResponse> customCoffeeList;
    private List<CustomizableStandardCoffeeResponse> customizableStandardCoffee;
    private WhereToDrink whereToDrink;
}