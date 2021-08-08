package org.fantasticcoffee.shop.repository.memory;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

public abstract class DefaultInMemoryRepository<T> implements Repository<T> {

    private static final Logger log = Logger.getLogger(DefaultInMemoryRepository.class.getName());

    private Map<Integer, T> database;

    public DefaultInMemoryRepository() {
        this.database = new HashMap<>();
    }

    public abstract Integer getIdForEntity(T entity);

    @Override
    public Optional<T> save(T entity) {

        if (entity == null) {
            log.error("Card number invalid");
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
            log.error("Null ID");
            throw new IllegalArgumentException("ID cannot be null");
        }
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public Optional<T> update(T entity) {

        if (entity == null) {
            log.error("Null entity");
            throw new IllegalArgumentException("Entity cannot be null");
        }
        Integer id = getIdForEntity(entity);
        return Optional.ofNullable(database.computeIfPresent(id, (k, v) -> entity));
    }

    @Override
    public Optional<T> delete(Integer id) {

        if (id == null) {
            log.error("Null ID");
            throw new IllegalArgumentException("ID cannot be null");
        }
        return Optional.ofNullable(database.remove(id));
    }
}