package org.example;
import org.example.DataBaseConnection.DataBaseConnection;
import org.example.Entities.Animal;
import java.sql.*;
import java.util.Scanner;
public class AnimalDao {
    public static void viewTable() {
        String query = "SELECT * FROM animal";
        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getString("id") + " | İsim: " + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("hata mesajı " + e.getMessage());
        }
    }

    public static void addAnimal(Animal animal) {
        String query = "INSERT INTO animal (name, voice, hungry_state) VALUES (?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, animal.getAnimalName());
            pstmt.setString(2, animal.getAnimalVoice());
            pstmt.setString(3, animal.getHungryState());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Kullanıcı başarıyla eklendi.");
            } else {
                System.out.println("Kullanıcı eklenirken bir hata oluştu.");
            }
        } catch (SQLException e) {
            System.out.println("Hata mesajı: " + e.getMessage());
        }
    }
    public static void addAnimal(Scanner scanner) {
        System.out.print("Hayvan Adı: ");
        String name = scanner.nextLine();

        System.out.print("Hayvan Ses: ");
        String animalVoice = scanner.nextLine();

        System.out.print("Açlık Durumu (Y/N): ");
        String hungryState = scanner.nextLine();

        Animal animal = new Animal(name, animalVoice, hungryState);
        AnimalDao.addAnimal(animal);

        System.out.println("Hayvan başarıyla eklendi.");
    }
    public static void makeNoise(int animalId) {
        String query = "SELECT * FROM animal WHERE id = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, animalId); // Parametreyi ayarla
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("name") + " Ses çıkarıyor: " + resultSet.getString("voice"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Hata mesajı: " + e.getMessage());
        }
    }
    public static void animalMakeNoise(Scanner scanner) {
        System.out.print("Hangi hayvanı seçiyorsunuz: ");
        int animalId = scanner.nextInt();
        scanner.nextLine(); // Boş satırı oku
        AnimalDao.makeNoise(animalId);
    }
}
