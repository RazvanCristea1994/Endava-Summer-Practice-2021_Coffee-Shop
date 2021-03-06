package org.fantasticcoffee.shop.data.standardrecipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StandardRecipeRequest {

    @NotNull(message = "Standard recipe name requested")
    @Pattern(regexp = "(?i)^(ESPRESSO|MACHIATTO|CAFFEE_LATTE|CAPPUCCINO|CAFFEE_MIEL|CUSTOM)$")
    private String standardRecipeName;
}