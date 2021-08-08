package org.fantasticcoffee.shop.data;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseWithList<T> {

    private final String coffeeShopName = "Fantastic Coffee";
    private final List<T> responseBody;

    public ResponseWithList(List<T> responseBody) {
        this.responseBody = responseBody;
    }
}