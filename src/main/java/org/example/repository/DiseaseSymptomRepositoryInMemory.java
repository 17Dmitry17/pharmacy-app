package org.example.repository;

import org.example.entities.DiseaseSymptom;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для хранения связей болезнь-симптом в памяти
 */
public class DiseaseSymptomRepositoryInMemory implements DiseaseSymptomRepository {
    private List<DiseaseSymptom> diseaseSymptoms = new ArrayList<>();

    @Override
    public void add(DiseaseSymptom diseaseSymptom) {
        diseaseSymptoms.add(diseaseSymptom);
    }

    @Override
    public List<String> getDiseaseNamesBySymptomName(String symptomName) {
        List<String> result = new ArrayList<>();
        // Ищем все болезни, которые имеют указанный симптом
        for (DiseaseSymptom ds : diseaseSymptoms) {
            if (ds.getSymptomName().equalsIgnoreCase(symptomName)) {
                result.add(ds.getDiseaseName());
            }
        }
        return result;
    }
}
