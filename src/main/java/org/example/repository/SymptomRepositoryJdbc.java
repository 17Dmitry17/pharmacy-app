package org.example.repository;

import org.example.database.DatabaseManager;
import org.example.entities.Symptom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC-реализация репозитория симптомов
 */
public class SymptomRepositoryJdbc implements SymptomRepository {

    @Override
    public void add(Symptom symptom) {
        String sql = "INSERT INTO symptoms (name) VALUES (?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, symptom.getName());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка добавления симптома", e);
        }
    }

    @Override
    public void remove(String name) {
        String sql = "DELETE FROM symptoms WHERE LOWER(name) = LOWER(?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления симптома", e);
        }
    }

    @Override
    public void update(String name, Symptom newSymptom) {
        String sql = "UPDATE symptoms SET name = ? WHERE LOWER(name) = LOWER(?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newSymptom.getName());
            ps.setString(2, name);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка обновления симптома", e);
        }
    }

    @Override
    public Symptom getByName(String name) {
        String sql = "SELECT * FROM symptoms WHERE LOWER(name) = LOWER(?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Symptom(rs.getString("name"));
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка поиска симптома", e);
        }
    }

    @Override
    public List<Symptom> getAll() {
        String sql = "SELECT * FROM symptoms ORDER BY name";
        List<Symptom> symptoms = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                symptoms.add(new Symptom(rs.getString("name")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения списка симптомов", e);
        }

        return symptoms;
    }
}
