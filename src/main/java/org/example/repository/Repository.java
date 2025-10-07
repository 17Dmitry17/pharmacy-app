package org.example.repository;

import java.util.List;

public interface Repository<T> {
    void add(T object);
    void remove(String name);
    void update(String name, T newObject);
    T getByName(String name);
    List<T> getAll();
}
