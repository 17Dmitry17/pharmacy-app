package org.example.repository;

import org.example.database.DatabaseManager;
import org.example.entities.Disease;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC-реализация репозитория болезней
 */
public class DiseaseRepositoryJdbc implements DiseaseRepository {

    @Override
    public void add(Disease disease) {
        String sql = "INSERT INTO diseases (name, description) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, disease.getName());
            ps.setString(2, disease.getDescription());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка добавления болезни", e);
        }
    }

    @Override
    public void remove(String name) {
        String sql = "DELETE FROM diseases WHERE LOWER(name) = LOWER(?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления болезни", e);
        }
    }

    @Override
    public void update(String name, Disease newDisease) {
        String sql = "UPDATE diseases SET name = ?, description = ? WHERE LOWER(name) = LOWER(?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newDisease.getName());
            ps.setString(2, newDisease.getDescription());
            ps.setString(3, name);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка обновления болезни", e);
        }
    }

    @Override
    public Disease getByName(String name) {
        String sql = "SELECT * FROM diseases WHERE LOWER(name) = LOWER(?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Disease(
                        rs.getString("name"),
                        rs.getString("description")
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка поиска болезни", e);
        }
    }

    @Override
    public List<Disease> getAll() {
        String sql = "SELECT * FROM diseases ORDER BY name";
        List<Disease> diseases = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                diseases.add(new Disease(
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения списка болезней", e);
        }

        return diseases;
    }
}
