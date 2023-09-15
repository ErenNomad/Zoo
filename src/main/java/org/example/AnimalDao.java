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
            System.out.println("Error message :" + e.getMessage());
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
                System.out.println("User added successfully.");
            } else {
                System.out.println("An error occurred while adding the user.");
            }
        } catch (SQLException e) {
            System.out.println("Error message : " + e.getMessage());
        }
    }
    public static void addAnimal(Scanner scanner) {
        System.out.print("Animal Name : ");
        String name = scanner.nextLine();

        System.out.print("Animal Voice : ");
        String animalVoice = scanner.nextLine();

        System.out.print("Hunger Situation (Y/N): ");
        String hungryState = scanner.nextLine();

        Animal animal = new Animal(name, animalVoice, hungryState);
        AnimalDao.addAnimal(animal);

        System.out.println("Animal added successfully.");
    }
    public static void makeNoise(int animalId) {
        String query = "SELECT * FROM animal WHERE id = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, animalId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("name") + " Ses çıkarıyor: " + resultSet.getString("voice"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error message : " + e.getMessage());
        }
    }
    public static void animalMakeNoise(Scanner scanner) {
        System.out.print("Which animal do you choose : ");
        int animalId = scanner.nextInt();
        scanner.nextLine();
        AnimalDao.makeNoise(animalId);
    }
}
