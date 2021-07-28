package org.fantasticcoffee.shop.facade.populator;

public interface Populator<Target, Source> {

    void populate(Target target, Source source);
}