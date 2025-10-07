package org.example;

import org.example.cli.Menu;
import org.example.repository.*;
import org.example.service.PharmacyService;
import org.example.service.PharmacyServiceImpl;

/**
 * Главный класс приложения "Домашняя аптечка"
 * Инициализирует все компоненты и запускает консольное меню
 */
public class Main {
    public static void main(String[] args) {
        //создаем репозитории для работы с данными в памяти
        MedicineRepository medicineRepository = new MedicineRepositoryInMemory();
        DiseaseRepository diseaseRepository = new DiseaseRepositoryInMemory();
        SymptomRepository symptomRepository = new SymptomRepositoryInMemory();
        DiseaseSymptomRepository diseaseSymptomRepository = new DiseaseSymptomRepositoryInMemory();

        //создаем сервис с внедрением зависимостей
        PharmacyService pharmacyService = new PharmacyServiceImpl(
                medicineRepository,
                diseaseRepository,
                symptomRepository,
                diseaseSymptomRepository
        );

        //запускаем консольное меню
        Menu menu = new Menu(pharmacyService);
        menu.run();
    }
}
