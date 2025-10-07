package org.example.cli;

import org.example.entities.Disease;
import org.example.entities.Medicine;
import org.example.entities.Symptom;
import org.example.service.PharmacyService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private PharmacyService pharmacyService;

    public Menu(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    public void run() {
        while (true) {
            printMainMenu();
            int choice = readInt();

            switch (choice) {
                case 1:
                    handleMedicines();
                    break;
                case 2:
                    handleDiseases();
                    break;
                case 3:
                    handleSymptoms();
                    break;
                case 4:
                    searchDiseasesBySymptom();
                    break;
                case 5:
                    searchMedicinesByDisease();
                    break;
                case 0:
                    System.out.println("Выход из программы");
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n=== Домашняя аптечка ===");
        System.out.println("1. Лекарства");
        System.out.println("2. Болезни");
        System.out.println("3. Симптомы");
        System.out.println("4. Поиск болезней по симптому");
        System.out.println("5. Поиск лекарств по болезни");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private void handleMedicines() {
        System.out.println("\n--- Лекарства ---");
        System.out.println("1. Добавить лекарство");
        System.out.println("2. Показать все лекарства");
        System.out.println("3. Удалить лекарство");
        System.out.println("4. Изменить лекарство");
        System.out.print("Выберите действие: ");

        int choice = readInt();

        switch (choice) {
            case 1:
                addMedicine();
                break;
            case 2:
                showAllMedicines();
                break;
            case 3:
                removeMedicine();
                break;
            case 4:
                updateMedicine();
                break;
        }
    }

    private void addMedicine() {
        scanner.nextLine();

        System.out.print("Введите название лекарства: ");
        String name = scanner.nextLine();

        System.out.print("Введите срок годности (формат: гггг-мм-дд): ");
        String dateStr = scanner.nextLine();
        LocalDate expirationDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);

        System.out.print("Введите название болезни: ");
        String diseaseName = scanner.nextLine();

        Medicine medicine = new Medicine(name, expirationDate, diseaseName);
        pharmacyService.addMedicine(medicine);

        System.out.println("Лекарство добавлено!");
    }

    private void showAllMedicines() {
        List<Medicine> medicines = pharmacyService.getAllMedicines();

        if (medicines.isEmpty()) {
            System.out.println("Нет лекарств в базе");
            return;
        }

        System.out.println("\nСписок лекарств:");
        for (Medicine medicine : medicines) {
            System.out.println(medicine);
        }
    }

    private void removeMedicine() {
        scanner.nextLine();
        System.out.print("Введите название лекарства для удаления: ");
        String name = scanner.nextLine();
        pharmacyService.removeMedicine(name);
        System.out.println("Лекарство удалено!");
    }

    private void updateMedicine() {
        scanner.nextLine();

        System.out.print("Введите название лекарства для изменения: ");
        String name = scanner.nextLine();

        Medicine existingMedicine = pharmacyService.getMedicineByName(name);
        if (existingMedicine == null) {
            System.out.println("Лекарство не найдено!");
            return;
        }

        System.out.print("Введите новое название: ");
        String newName = scanner.nextLine();

        System.out.print("Введите новый срок годности (формат: гггг-мм-дд): ");
        String dateStr = scanner.nextLine();
        LocalDate expirationDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);

        System.out.print("Введите название болезни: ");
        String diseaseName = scanner.nextLine();

        Medicine updatedMedicine = new Medicine(newName, expirationDate, diseaseName);
        pharmacyService.updateMedicine(name, updatedMedicine);

        System.out.println("Лекарство изменено!");
    }

    private void handleDiseases() {
        System.out.println("\n--- Болезни ---");
        System.out.println("1. Добавить болезнь");
        System.out.println("2. Показать все болезни");
        System.out.println("3. Связать болезнь с симптомом");
        System.out.print("Выберите действие: ");

        int choice = readInt();

        switch (choice) {
            case 1:
                addDisease();
                break;
            case 2:
                showAllDiseases();
                break;
            case 3:
                linkDiseaseWithSymptom();
                break;
        }
    }

    private void addDisease() {
        scanner.nextLine();

        System.out.print("Введите название болезни: ");
        String name = scanner.nextLine();

        System.out.print("Введите описание: ");
        String description = scanner.nextLine();

        Disease disease = new Disease(name, description);
        pharmacyService.addDisease(disease);

        System.out.println("Болезнь добавлена!");
    }

    private void showAllDiseases() {
        List<Disease> diseases = pharmacyService.getAllDiseases();

        if (diseases.isEmpty()) {
            System.out.println("Нет болезней в базе");
            return;
        }

        System.out.println("\nСписок болезней:");
        for (Disease disease : diseases) {
            System.out.println(disease);
        }
    }

    private void linkDiseaseWithSymptom() {
        scanner.nextLine();

        System.out.print("Введите название болезни: ");
        String diseaseName = scanner.nextLine();

        System.out.print("Введите название симптома: ");
        String symptomName = scanner.nextLine();

        pharmacyService.linkDiseaseWithSymptom(diseaseName, symptomName);
        System.out.println("Связь создана!");
    }

    private void handleSymptoms() {
        System.out.println("\n--- Симптомы ---");
        System.out.println("1. Добавить симптом");
        System.out.println("2. Показать все симптомы");
        System.out.print("Выберите действие: ");

        int choice = readInt();

        switch (choice) {
            case 1:
                addSymptom();
                break;
            case 2:
                showAllSymptoms();
                break;
        }
    }

    private void addSymptom() {
        scanner.nextLine();

        System.out.print("Введите название симптома: ");
        String name = scanner.nextLine();

        Symptom symptom = new Symptom(name);
        pharmacyService.addSymptom(symptom);

        System.out.println("Симптом добавлен!");
    }

    private void showAllSymptoms() {
        List<Symptom> symptoms = pharmacyService.getAllSymptoms();

        if (symptoms.isEmpty()) {
            System.out.println("Нет симптомов в базе");
            return;
        }

        System.out.println("\nСписок симптомов:");
        for (Symptom symptom : symptoms) {
            System.out.println(symptom);
        }
    }

    private void searchDiseasesBySymptom() {
        scanner.nextLine();

        System.out.print("Введите название симптома: ");
        String symptomName = scanner.nextLine();

        List<Disease> diseases = pharmacyService.findDiseasesBySymptom(symptomName);

        if (diseases.isEmpty()) {
            System.out.println("Болезни с таким симптомом не найдены");
            return;
        }

        System.out.println("\nНайденные болезни:");
        for (Disease disease : diseases) {
            System.out.println(disease);
        }
    }

    private void searchMedicinesByDisease() {
        scanner.nextLine();

        System.out.print("Введите название болезни: ");
        String diseaseName = scanner.nextLine();

        List<Medicine> medicines = pharmacyService.findMedicinesByDisease(diseaseName);

        if (medicines.isEmpty()) {
            System.out.println("Лекарства для этой болезни не найдены");
            return;
        }

        System.out.println("\nНайденные лекарства:");
        for (Medicine medicine : medicines) {
            System.out.println(medicine);
        }
    }

    private int readInt() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
}
