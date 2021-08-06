package org.fantasticcoffee.shop.repository.memory;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    Optional<T> save(T entity);

    List<T> findAll();

    Optional<T> find(Integer id);

    Optional<T> update(T entity);

    Optional<T> delete(Integer id);
}