package org.fantasticcoffee.shop.validator;

import org.springframework.stereotype.Component;

@Component
public class CardValidation {

    public static void cardNumberValidation(String cardNumber) {

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

        if (numberSum % 10 != 0) {
            throw new IllegalArgumentException("Card number invalid");
        }
    }
}