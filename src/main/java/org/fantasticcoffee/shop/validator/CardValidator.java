package org.fantasticcoffee.shop.validator;

import org.fantasticcoffee.shop.model.Card;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;

@Component
public class CardValidator {

    public void validateCard(Card card) {

        cardNumberValidation(card.getCardNumber());
        civValidation(card.getCiv());
    }

    private void cardNumberValidation(String cardNumber) {

        int numberDigits = cardNumber.length();
        int numberSum = 0;
        boolean isSecond = false;
        for (int i = numberDigits - 1; i >= 0; i--) {
            int d = cardNumber.charAt(i) - '0';

            if (isSecond) {
                d = d * 2;
            }

            numberSum += d / 10;
            numberSum += d % 10;

            isSecond = !isSecond;
        }

        if (numberSum % 10 != 0 || !cardNumber.matches("[\\d]{16}")) {
            throw new IllegalArgumentException("22");
        }
    }

    private void civValidation(String civ) {

        if (!civ.matches("[\\d]{3}")) {
            throw new IllegalArgumentException("97");
        }
    }
}
