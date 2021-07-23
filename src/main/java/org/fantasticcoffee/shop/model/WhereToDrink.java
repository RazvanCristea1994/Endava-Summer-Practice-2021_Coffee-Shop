package org.fantasticcoffee.shop.model;

import lombok.Getter;

@Getter
public enum WhereToDrink {
    PICK_UP("Pick-Up"), TO_GO("To-Go");

    private String name;

    WhereToDrink(String name) {
        this.name = name;
    }
}