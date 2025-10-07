package org.example;

import org.example.cli.Menu;
import org.example.repository.*;
import org.example.service.PharmacyService;
import org.example.service.PharmacyServiceImpl;

public class Main {
    public static void main(String[] args) {
        //создаем репозитории
        MedicineRepository medicineRepository = new MedicineRepositoryInMemory();
        DiseaseRepository diseaseRepository = new DiseaseRepositoryInMemory();
        SymptomRepository symptomRepository = new SymptomRepositoryInMemory();
        DiseaseSymptomRepository diseaseSymptomRepository = new DiseaseSymptomRepositoryInMemory();

        //создаем сервис и передаем репозитории
        PharmacyService pharmacyService = new PharmacyServiceImpl(
                medicineRepository,
                diseaseRepository,
                symptomRepository,
                diseaseSymptomRepository
        );

        //создаем меню и запускаем приложение
        Menu menu = new Menu(pharmacyService);
        menu.run();
    }
}
