package org.example.repository;

import org.example.entities.DiseaseSymptom;
import java.util.List;

public interface DiseaseSymptomRepository {
    void add(DiseaseSymptom diseaseSymptom);
    List<String> getDiseaseNamesBySymptomName(String symptomName);
}
