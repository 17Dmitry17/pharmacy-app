package org.example.entities;

import java.time.LocalDate;

public class Medicine {
    private String name;
    private LocalDate expirationDate;
    private String diseaseName;


    public Medicine(String name, LocalDate expirationDate, String diseaseName) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.diseaseName = diseaseName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    @Override
    public String toString() {
        return "Лекарство: " + name + ", срок годности: " + expirationDate +
                ", для болезни: " + diseaseName;
    }
}
