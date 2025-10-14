package org.example.repository;

import java.util.List;

/**
 * Базовый интерфейс для всех репозиториев
 * Определяет стандартные CRUD операции
 */
public interface Repository<T> {
    void add(T object); // Добавить объект
    void remove(String name); // Удалить по названию
    void update(String name, T newObject); // Обновить объект
    T getByName(String name); // Найти по названию
    List<T> getAll(); // Получить все объекты
}
