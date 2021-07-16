package org.fantasticcoffee.shop.model;

public class AbstractEntity {

    protected Integer id;

    public void setId(Integer id) {
        if (this.id == null) {
            this.id = id;
        }
    }

    public Integer getId() {
        return this.id;
    }
}
