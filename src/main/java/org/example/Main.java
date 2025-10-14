package org.example;

import org.example.cli.Menu;
import org.example.repository.*;
import org.example.service.PharmacyService;
import org.example.service.PharmacyServiceImpl;

public class Main {
    public static void main(String[] args) {
        // Создаём репозитории для работы с данными в памяти (InMemory)
        MedicineRepository medicineRepository = new MedicineRepositoryInMemory();
        DiseaseRepository diseaseRepository = new DiseaseRepositoryInMemory();
        SymptomRepository symptomRepository = new SymptomRepositoryInMemory();
        DiseaseSymptomRepository diseaseSymptomRepository = new DiseaseSymptomRepositoryInMemory();

        // Создаём сервис с внедрением зависимостей
        PharmacyService pharmacyService = new PharmacyServiceImpl(
                medicineRepository,
                diseaseRepository,
                symptomRepository,
                diseaseSymptomRepository
        );

        Menu menu = new Menu(pharmacyService);
        menu.run();
    }
}
