package org.example.repository;

import org.example.entities.Disease;
import java.util.ArrayList;
import java.util.List;


//Репозиторий для хранения болезней в оперативной памяти (RAM)
public class DiseaseRepositoryInMemory implements DiseaseRepository {
    private List<Disease> diseases = new ArrayList<>(); // Список болезней в памяти

    @Override
    public void add(Disease disease) {
        diseases.add(disease); // Добавляем болезнь в список
    }

    @Override
    public void remove(String name) {
        // Удаляем все болезни с указанным названием (без учёта регистра)
        diseases.removeIf(disease -> disease.getName().equalsIgnoreCase(name));
    }

    @Override
    public void update(String name, Disease newDisease) {
        // Ищем болезнь и заменяем на новую
        for (int i = 0; i < diseases.size(); i++) {
            if (diseases.get(i).getName().equalsIgnoreCase(name)) {
                diseases.set(i, newDisease);
                break; // Прерываем после первого совпадения
            }
        }
    }

    @Override
    public Disease getByName(String name) {
        // Ищем болезнь по названию
        for (Disease disease : diseases) {
            if (disease.getName().equalsIgnoreCase(name)) {
                return disease;
            }
        }
        return null; // Если не найдена
    }

    @Override
    public List<Disease> getAll() {
        return new ArrayList<>(diseases); // Возвращаем копию списка
    }
}
