package org.fantasticcoffee.shop.facade.converter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface Converter<Target, Source> {

    Target convert(Source source);

    default List<Target> convertAll(List<Source> sources) {

        return sources.stream()
                .map(this::convert)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}