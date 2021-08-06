package org.fantasticcoffee.shop.data.card;

import lombok.Getter;

@Getter
public class CardResponse {

    String cardHolderName;

    public CardResponse(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
}