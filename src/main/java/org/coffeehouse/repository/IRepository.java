package org.coffeehouse.repository;

import org.coffeehouse.model.AbstractEntity;

import java.util.Optional;

public interface IRepository<ID, T extends AbstractEntity<ID>> {

    Optional<T> save(T entity) throws IllegalArgumentException;

    Iterable<T> findAll();

    Optional<T> find(ID id) throws IllegalArgumentException;

    Optional<T> update(T entity) throws IllegalArgumentException;

    Optional<T> delete(ID id) throws IllegalArgumentException;
}
