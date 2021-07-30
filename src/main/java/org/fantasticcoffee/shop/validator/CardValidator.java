package org.fantasticcoffee.shop.validator;

import org.fantasticcoffee.shop.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardValidator {

    public boolean isCardNumberValid (Card card) {

        Long digitNumber = card.getCardNumber();
        long digit = 0;
        int i = 0;
        int sum = 0;

        while (digitNumber > 0) {
            digit = digitNumber % 10;
            digitNumber /= 10;

            if (i % 2 != 0) {
                digit *= 2;
            }

            if (digit > 9) {
                digit = (digit % 10) + 1;
            }

            sum += digit;
            i++;
        }

        return sum % 10 == 0;
    }
}
