package org.example.service;

import org.example.entities.Disease;
import org.example.entities.Medicine;
import org.example.entities.Symptom;
import java.util.List;

/**
 * Интерфейс сервиса для управления домашней аптечкой
 * Определяет бизнес-логику приложения
 */
public interface PharmacyService {
    // Методы для работы с лекарствами
    void addMedicine(Medicine medicine);
    void removeMedicine(String name);
    void updateMedicine(String name, Medicine medicine);
    Medicine getMedicineByName(String name);
    List<Medicine> getAllMedicines();
    List<Medicine> findMedicinesByDisease(String diseaseName);

    // Методы для работы с болезнями
    void addDisease(Disease disease);
    Disease getDiseaseByName(String name);
    List<Disease> getAllDiseases();
    List<Disease> findDiseasesBySymptom(String symptomName);

    // Методы для работы с симптомами
    void addSymptom(Symptom symptom);
    List<Symptom> getAllSymptoms();

    // Связь болезнь-симптом
    void linkDiseaseWithSymptom(String diseaseName, String symptomName);
}
