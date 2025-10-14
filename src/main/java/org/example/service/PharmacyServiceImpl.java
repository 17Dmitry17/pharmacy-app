package org.example.service;

import org.example.entities.Disease;
import org.example.entities.DiseaseSymptom;
import org.example.entities.Medicine;
import org.example.entities.Symptom;
import org.example.repository.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация сервиса домашней аптечки
 * Содержит бизнес-логику приложения
 */
public class PharmacyServiceImpl implements PharmacyService {
    private MedicineRepository medicineRepository;
    private DiseaseRepository diseaseRepository;
    private SymptomRepository symptomRepository;
    private DiseaseSymptomRepository diseaseSymptomRepository;

    // Конструктор с внедрением зависимостей (Dependency Injection)
    public PharmacyServiceImpl(MedicineRepository medicineRepository,
                               DiseaseRepository diseaseRepository,
                               SymptomRepository symptomRepository,
                               DiseaseSymptomRepository diseaseSymptomRepository) {
        this.medicineRepository = medicineRepository;
        this.diseaseRepository = diseaseRepository;
        this.symptomRepository = symptomRepository;
        this.diseaseSymptomRepository = diseaseSymptomRepository;
    }

    @Override
    public void addMedicine(Medicine medicine) {
        medicineRepository.add(medicine);
    }

    @Override
    public void removeMedicine(String name) {
        medicineRepository.remove(name);
    }

    @Override
    public void updateMedicine(String name, Medicine medicine) {
        medicineRepository.update(name, medicine);
    }

    @Override
    public Medicine getMedicineByName(String name) {
        return medicineRepository.getByName(name);
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.getAll();
    }

    @Override
    public void addDisease(Disease disease) {
        diseaseRepository.add(disease);
    }

    @Override
    public List<Disease> getAllDiseases() {
        return diseaseRepository.getAll();
    }

    @Override
    public Disease getDiseaseByName(String name) {
        return diseaseRepository.getByName(name);
    }

    @Override
    public void addSymptom(Symptom symptom) {
        symptomRepository.add(symptom);
    }

    @Override
    public List<Symptom> getAllSymptoms() {
        return symptomRepository.getAll();
    }

    @Override
    public void linkDiseaseWithSymptom(String diseaseName, String symptomName) {
        // Создание связи через конструктор Lombok
        DiseaseSymptom link = new DiseaseSymptom(diseaseName, symptomName);
        diseaseSymptomRepository.add(link);
    }

    @Override
    public List<Disease> findDiseasesBySymptom(String symptomName) {
        // Получаем названия болезней по симптому
        List<String> diseaseNames = diseaseSymptomRepository.getDiseaseNamesBySymptomName(symptomName);

        List<Disease> diseases = new ArrayList<>();
        // Для каждого названия получаем полный объект болезни
        for (String diseaseName : diseaseNames) {
            Disease disease = diseaseRepository.getByName(diseaseName);
            if (disease != null) {
                diseases.add(disease);
            }
        }
        return diseases;
    }

    @Override
    public List<Medicine> findMedicinesByDisease(String diseaseName) {
        return medicineRepository.getByDiseaseName(diseaseName);
    }
}
