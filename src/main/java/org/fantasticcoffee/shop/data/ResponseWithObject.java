package org.fantasticcoffee.shop.data;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseWithObject<T> {

    private final String coffeeShopName = "Fantastic Coffee";
    private final T responseBody;

    public ResponseWithObject(T responseBody) {
        this.responseBody = responseBody;
    }
}