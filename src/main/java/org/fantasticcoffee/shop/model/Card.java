package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Card {

    Long cardNumber;
    String cardHolderName;
    LocalDate expiry;
    Integer civ;
}