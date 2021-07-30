package org.fantasticcoffee.shop.facade.converter.card;

import org.fantasticcoffee.shop.data.CardRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardConverter implements Converter<Card, CardRequest> {

    @Override
    public Card convert(CardRequest cardRequest) {
        return new Card(cardRequest.getCardHolderName(),
                cardRequest.getCardNumber(),
                cardRequest.getExpiry(),
                cardRequest.getCiv());
    }
}