package org.example.service;

import org.example.entities.Disease;
import org.example.entities.Medicine;
import org.example.entities.Symptom;
import java.util.List;

public interface PharmacyService {
    void addMedicine(Medicine medicine);
    void removeMedicine(String name);
    void updateMedicine(String name, Medicine medicine);
    Medicine getMedicineByName(String name);
    List<Medicine> getAllMedicines();

    void addDisease(Disease disease);
    List<Disease> getAllDiseases();
    Disease getDiseaseByName(String name);

    void addSymptom(Symptom symptom);
    List<Symptom> getAllSymptoms();

    void linkDiseaseWithSymptom(String diseaseName, String symptomName);
    List<Disease> findDiseasesBySymptom(String symptomName);
    List<Medicine> findMedicinesByDisease(String diseaseName);
}
