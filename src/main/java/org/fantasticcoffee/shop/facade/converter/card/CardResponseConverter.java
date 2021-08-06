package org.fantasticcoffee.shop.facade.converter.card;

import org.fantasticcoffee.shop.data.card.CardResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardResponseConverter implements Converter<CardResponse, Card> {

    @Override
    public CardResponse convert(Card card) {
        return new CardResponse(card.getCardHolderName());
    }
}