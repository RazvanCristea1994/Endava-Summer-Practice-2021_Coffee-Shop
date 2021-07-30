package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
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