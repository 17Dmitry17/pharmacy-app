package org.example.repository;

import org.example.entities.Disease;
import java.util.ArrayList;
import java.util.List;

public class DiseaseRepositoryInMemory implements DiseaseRepository {
    private List<Disease> diseases = new ArrayList<>();

    @Override
    public void add(Disease disease) {
        diseases.add(disease);
    }

    @Override
    public void remove(String name) {
        diseases.removeIf(disease -> disease.getName().equalsIgnoreCase(name));
    }

    @Override
    public void update(String name, Disease newDisease) {
        for (int i = 0; i < diseases.size(); i++) {
            if (diseases.get(i).getName().equalsIgnoreCase(name)) {
                diseases.set(i, newDisease);
                break;
            }
        }
    }

    @Override
    public Disease getByName(String name) {
        for (Disease disease : diseases) {
            if (disease.getName().equalsIgnoreCase(name)) {
                return disease;
            }
        }
        return null;
    }

    @Override
    public List<Disease> getAll() {
        return new ArrayList<>(diseases);
    }
}
