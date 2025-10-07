package org.example.entities;

public class DiseaseSymptom {
    private String diseaseName;
    private String symptomName;


    public DiseaseSymptom(String diseaseName, String symptomName) {
        this.diseaseName = diseaseName;
        this.symptomName = symptomName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }
}
