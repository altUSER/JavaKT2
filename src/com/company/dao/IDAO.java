package com.company.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDAO<T> {
    T get(UUID id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
