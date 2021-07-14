package org.coffeehouse.model;

public class AbstractEntity<ID> {

    protected ID id;

    public void setId(ID id) {
        if (this.id == null) {
            this.id = id;
        }
    }

    public ID getId() {
        return this.id;
    }
}
