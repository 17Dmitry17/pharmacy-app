package org.example.repository;

import org.example.entities.Medicine;
import java.util.ArrayList;
import java.util.List;

public class MedicineRepositoryInMemory implements MedicineRepository {
    private List<Medicine> medicines = new ArrayList<>();

    @Override
    public void add(Medicine medicine) {
        medicines.add(medicine);
    }

    @Override
    public void remove(String name) {
        medicines.removeIf(medicine -> medicine.getName().equalsIgnoreCase(name));
    }

    @Override
    public void update(String name, Medicine newMedicine) {
        for (int i = 0; i < medicines.size(); i++) {
            if (medicines.get(i).getName().equalsIgnoreCase(name)) {
                medicines.set(i, newMedicine);
                break;
            }
        }
    }

    @Override
    public Medicine getByName(String name) {
        for (Medicine medicine : medicines) {
            if (medicine.getName().equalsIgnoreCase(name)) {
                return medicine;
            }
        }
        return null;
    }

    @Override
    public List<Medicine> getAll() {
        return new ArrayList<>(medicines);
    }

    @Override
    public List<Medicine> getByDiseaseName(String diseaseName) {
        List<Medicine> result = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (medicine.getDiseaseName().equalsIgnoreCase(diseaseName)) {
                result.add(medicine);
            }
        }
        return result;
    }
}
