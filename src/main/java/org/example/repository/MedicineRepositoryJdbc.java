package org.example.repository;

import org.example.database.DatabaseManager;
import org.example.entities.Medicine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineRepositoryJdbc implements MedicineRepository {

    @Override
    public void add(Medicine medicine) {
        String sql = "INSERT INTO medicines (name, expiration_date, disease_name) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, medicine.getName());
            ps.setDate(2, Date.valueOf(medicine.getExpirationDate()));
            ps.setString(3, medicine.getDiseaseName());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка добавления лекарства", e);
        }
    }

    @Override
    public void remove(String name) {
        String sql = "DELETE FROM medicines WHERE LOWER(name) = LOWER(?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления лекарства", e);
        }
    }

    @Override
    public void update(String name, Medicine newMedicine) {
        String sql = "UPDATE medicines SET name = ?, expiration_date = ?, disease_name = ? WHERE LOWER(name) = LOWER(?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newMedicine.getName());
            ps.setDate(2, Date.valueOf(newMedicine.getExpirationDate()));
            ps.setString(3, newMedicine.getDiseaseName());
            ps.setString(4, name);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка обновления лекарства", e);
        }
    }

    @Override
    public Medicine getByName(String name) {
        String sql = "SELECT * FROM medicines WHERE LOWER(name) = LOWER(?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Medicine(
                        rs.getString("name"),
                        rs.getDate("expiration_date").toLocalDate(),
                        rs.getString("disease_name")
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка поиска лекарства", e);
        }
    }

    @Override
    public List<Medicine> getAll() {
        String sql = "SELECT * FROM medicines ORDER BY name";
        List<Medicine> medicines = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                medicines.add(new Medicine(
                        rs.getString("name"),
                        rs.getDate("expiration_date").toLocalDate(),
                        rs.getString("disease_name")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения списка лекарств", e);
        }

        return medicines;
    }

    @Override
    public List<Medicine> getByDiseaseName(String diseaseName) {
        String sql = "SELECT * FROM medicines WHERE LOWER(disease_name) = LOWER(?)";
        List<Medicine> medicines = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, diseaseName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                medicines.add(new Medicine(
                        rs.getString("name"),
                        rs.getDate("expiration_date").toLocalDate(),
                        rs.getString("disease_name")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка поиска лекарств по болезни", e);
        }

        return medicines;
    }
}
