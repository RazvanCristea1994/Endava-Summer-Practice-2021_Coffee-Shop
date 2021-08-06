package org.fantasticcoffee.shop.repository.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

public abstract class DefaultInMemoryRepository<T> implements Repository<T> {

    private Map<Integer, T> database;

    public DefaultInMemoryRepository() {
        this.database = new HashMap<>();
    }

    public abstract Integer getIdForEntity(T entity);

    @Override
    public Optional<T> save(T entity) {

        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        Integer id = getIdForEntity(entity);
        return Optional.ofNullable(database.putIfAbsent(id, entity));
    }

    @Override
    public List<T> findAll() {

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
        Integer id = getIdForEntity(entity);
        return Optional.ofNullable(database.computeIfPresent(id, (k, v) -> entity));
    }

    @Override
    public Optional<T> delete(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return Optional.ofNullable(database.remove(id));
    }
}