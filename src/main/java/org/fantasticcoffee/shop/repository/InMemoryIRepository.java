package org.fantasticcoffee.shop.repository;

import org.fantasticcoffee.shop.model.AbstractEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

public class InMemoryIRepository<T extends AbstractEntity> implements IRepository<T> {

    private Map<Integer, T> database;

    public InMemoryIRepository() {
        this.database = new HashMap<>();
    }

    @Override
    public Optional<T> save(T entity) {

        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        return Optional.ofNullable(database.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Iterable<T> findAll() {

        List<T> results = new ArrayList<>();
        for (T entity : database.values()) {
            results.add(entity);
        }

        return results;
    }

    @Override
    public Optional<T> find(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public Optional<T> update(T entity) {

        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        return Optional.ofNullable(database.computeIfPresent(entity.getId(), (k, v) -> entity));
    }

    @Override
    public Optional<T> delete(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return Optional.ofNullable(database.remove(id));
    }
}
