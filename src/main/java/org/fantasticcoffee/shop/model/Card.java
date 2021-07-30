package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Card {

    String cardNumber;
    @NotNull(message = "Card holder name requested")
    @Pattern(regexp = "^[a-z '-]+$")
    String cardHolderName;
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    LocalDate expiry;
    String civ;
}