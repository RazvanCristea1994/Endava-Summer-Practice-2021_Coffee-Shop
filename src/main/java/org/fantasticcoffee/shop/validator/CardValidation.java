package org.fantasticcoffee.shop.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CardValidation {

    static Logger log = Logger.getLogger(CardValidation.class.getName());

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
            log.error("Card number invalid");
            throw new IllegalArgumentException("Card number invalid");
        }
    }
}