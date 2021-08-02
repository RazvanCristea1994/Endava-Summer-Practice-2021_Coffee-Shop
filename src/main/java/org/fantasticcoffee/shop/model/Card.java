package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Card {

    String cardHolderName;
    String cardNumber;
    LocalDate expiry;
    String civ;

    public Card(String cardHolderName,
                String cardNumber,
                LocalDate expiry,
                String civ) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiry = expiry;
        this.civ = civ;
    }
}