package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:h2:./pharmacy-db;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    // Статический блок для загрузки драйвера
    static {
        try {
            Class.forName("org.h2.Driver");
            System.out.println("✅ H2 драйвер загружен");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ H2 драйвер НЕ найден! Проверь pom.xml");
            throw new RuntimeException("H2 драйвер отсутствует", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS medicines (
                    name VARCHAR(100) PRIMARY KEY,
                    expiration_date DATE NOT NULL,
                    disease_name VARCHAR(100) NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS diseases (
                    name VARCHAR(100) PRIMARY KEY,
                    description VARCHAR(500)
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS symptoms (
                    name VARCHAR(100) PRIMARY KEY
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS disease_symptoms (
                    disease_name VARCHAR(100) NOT NULL,
                    symptom_name VARCHAR(100) NOT NULL,
                    PRIMARY KEY (disease_name, symptom_name),
                    FOREIGN KEY (disease_name) REFERENCES diseases(name) ON DELETE CASCADE,
                    FOREIGN KEY (symptom_name) REFERENCES symptoms(name) ON DELETE CASCADE
                )
            """);

            System.out.println("✅ База данных инициализирована (файл: pharmacy-db.mv.db)");

        } catch (SQLException e) {
            System.err.println("❌ Ошибка инициализации БД: " + e.getMessage());
            throw new RuntimeException("Не удалось инициализировать БД", e);
        }
    }
}
