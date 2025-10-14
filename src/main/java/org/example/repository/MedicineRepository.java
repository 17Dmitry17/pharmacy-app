package org.example.repository;

import org.example.entities.Medicine;
import java.util.List;

/**
 * Интерфейс репозитория для лекарств
 * Добавляет специфичный метод поиска по болезни
 */
public interface MedicineRepository extends Repository<Medicine> {
    List<Medicine> getByDiseaseName(String diseaseName); // Найти лекарства для болезни
}
