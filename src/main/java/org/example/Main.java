package org.example;

import org.example.cli.Menu;
import org.example.database.DatabaseManager;
import org.example.repository.*;
import org.example.service.PharmacyService;
import org.example.service.PharmacyServiceImpl;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Домашняя аптечка ===");
        System.out.println("Выберите тип хранилища:");
        System.out.println("1 - В памяти (InMemory)");
        System.out.println("2 - База данных (H2)");

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Ваш выбор: ");
        int choice = scanner.nextInt();

        PharmacyService pharmacyService;

        if (choice == 2) {
            DatabaseManager.initializeDatabase();

            MedicineRepository medicineRepository = new MedicineRepositoryJdbc();
            DiseaseRepository diseaseRepository = new DiseaseRepositoryJdbc();
            SymptomRepository symptomRepository = new SymptomRepositoryJdbc();
            DiseaseSymptomRepository diseaseSymptomRepository = new DiseaseSymptomRepositoryJdbc();

            pharmacyService = new PharmacyServiceImpl(
                    medicineRepository,
                    diseaseRepository,
                    symptomRepository,
                    diseaseSymptomRepository
            );

            System.out.println("Используется база данных H2");
        } else {
            MedicineRepository medicineRepository = new MedicineRepositoryInMemory();
            DiseaseRepository diseaseRepository = new DiseaseRepositoryInMemory();
            SymptomRepository symptomRepository = new SymptomRepositoryInMemory();
            DiseaseSymptomRepository diseaseSymptomRepository = new DiseaseSymptomRepositoryInMemory();

            pharmacyService = new PharmacyServiceImpl(
                    medicineRepository,
                    diseaseRepository,
                    symptomRepository,
                    diseaseSymptomRepository
            );

            System.out.println("Используется хранилище в памяти");
        }

        // Запускаем меню
        Menu menu = new Menu(pharmacyService);
        menu.run();
    }
}
