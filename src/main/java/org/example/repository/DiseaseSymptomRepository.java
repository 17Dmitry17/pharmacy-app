package org.example.repository;

import org.example.entities.DiseaseSymptom;
import java.util.List;

/**
 * Интерфейс репозитория для связей болезнь-симптом
 */
public interface DiseaseSymptomRepository {
    void add(DiseaseSymptom diseaseSymptom); // Добавить связь
    List<String> getDiseaseNamesBySymptomName(String symptomName); // Найти болезни по симптому
}
