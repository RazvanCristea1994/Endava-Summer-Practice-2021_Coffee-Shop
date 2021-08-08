package org.fantasticcoffee.shop.data.card;

import lombok.Getter;

@Getter
public class CardResponse {

    private final String cardHolderName;
    private final String cardNumber = "*".repeat(16);
    private final String expiry = "*".repeat(4) + "-" + "*".repeat(2) + "-" + "*".repeat(2);
    private final String civ = "*".repeat(3);

    public CardResponse(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
}