package org.example.repository;

import org.example.database.DatabaseManager;
import org.example.entities.DiseaseSymptom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiseaseSymptomRepositoryJdbc implements DiseaseSymptomRepository {

    @Override
    public void add(DiseaseSymptom diseaseSymptom) {
        String sql = "INSERT INTO disease_symptoms (disease_name, symptom_name) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, diseaseSymptom.getDiseaseName());
            ps.setString(2, diseaseSymptom.getSymptomName());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка добавления связи болезнь-симптом", e);
        }
    }

    @Override
    public List<String> getDiseaseNamesBySymptomName(String symptomName) {
        String sql = "SELECT disease_name FROM disease_symptoms WHERE LOWER(symptom_name) = LOWER(?)";
        List<String> diseaseNames = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, symptomName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                diseaseNames.add(rs.getString("disease_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка поиска болезней по симптому", e);
        }

        return diseaseNames;
    }
}
