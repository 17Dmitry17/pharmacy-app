package org.example.repository;

import org.example.entities.Medicine;
import java.util.List;

public interface MedicineRepository extends Repository<Medicine> {
    List<Medicine> getByDiseaseName(String diseaseName);
}
