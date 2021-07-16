package org.fantasticcoffee.shop.repository;

import org.fantasticcoffee.shop.model.AbstractEntity;

import java.util.Optional;

public interface IRepository<T extends AbstractEntity> {

    Optional<T> save(T entity);

    Iterable<T> findAll();

    Optional<T> find(Integer id);

    Optional<T> update(T entity);

    Optional<T> delete(Integer id);
}
