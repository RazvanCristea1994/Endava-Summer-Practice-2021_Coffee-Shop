package org.fantasticcoffee.shop.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum OrderType implements Serializable {
    PICK_UP("Pick-Up"), TO_GO("To-Go");

    private String name;

    OrderType(String name) {
        this.name = name;
    }
}