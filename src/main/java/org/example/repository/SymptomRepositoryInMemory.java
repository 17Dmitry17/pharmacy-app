package org.example.repository;

import org.example.entities.Symptom;
import java.util.ArrayList;
import java.util.List;

public class SymptomRepositoryInMemory implements SymptomRepository {
    private List<Symptom> symptoms = new ArrayList<>();

    @Override
    public void add(Symptom symptom) {
        symptoms.add(symptom);
    }

    @Override
    public void remove(String name) {
        symptoms.removeIf(symptom -> symptom.getName().equalsIgnoreCase(name));
    }

    @Override
    public void update(String name, Symptom newSymptom) {
        for (int i = 0; i < symptoms.size(); i++) {
            if (symptoms.get(i).getName().equalsIgnoreCase(name)) {
                symptoms.set(i, newSymptom);
                break;
            }
        }
    }

    @Override
    public Symptom getByName(String name) {
        for (Symptom symptom : symptoms) {
            if (symptom.getName().equalsIgnoreCase(name)) {
                return symptom;
            }
        }
        return null;
    }

    @Override
    public List<Symptom> getAll() {
        return new ArrayList<>(symptoms);
    }
}
