package org.example;
import org.example.DataBaseConnection.DataBaseConnection;
import org.example.Entities.Person;
import java.sql.*;
import java.util.Scanner;

public class PersonDao {
    public static Person getPersonByUsername(String username) {
        String query = "SELECT * FROM person WHERE user_name = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    // int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String last_name = resultSet.getString("last_name");
                    String password = resultSet.getString("password");
                    String role = resultSet.getString("role");
                    return new Person( name, last_name, password, username, role);
                }
            }
        } catch (SQLException e) {
            System.out.println("Hata mesajı: " + e.getMessage());
        }
        return null;
    }
    public static void viewTable() {
        String query = "SELECT * FROM person";
        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.println("ID: "+resultSet.getString("id")+" | Name: "+ resultSet.getString("name")+" | User Name : "+resultSet.getString("user_name")+" | Role: "+ resultSet.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println("hata mesajı " + e.getMessage());
        }
    }
    public static void addPerson(Person person) {
        String query = "INSERT INTO person (name, last_name, password, user_name, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getLastname());
            pstmt.setString(3, person.getPassword());
            pstmt.setString(4, person.getUserName());
            pstmt.setString(5, person.getRole());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("An error occurred while adding the user.");
            }
        } catch (SQLException e) {
            System.out.println("Hata mesajı: " + e.getMessage());
        }
    }
    public static void deletePerson(int personId) {
        String query = "DELETE FROM person WHERE id = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, personId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User is not found .");
            }
        } catch (SQLException e) {
            System.out.println("Hata mesajı :" + e.getMessage());
        }
    }
    public static void addUser(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Lastname: ");
        String lastName = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("User Name: ");
        String userName = scanner.nextLine();

        System.out.print("Rol (VISITOR or ADMIN): ");
        String role = scanner.nextLine();

        Person person = new Person(name, lastName, password, userName, role);
        PersonDao.addPerson(person);

        System.out.println("Kullanıcı başarıyla eklendi.");
    }

    public static void deleteUser(Scanner scanner) {
        System.out.print("Enter the ID of the user to be deleted: ");
        int personId = scanner.nextInt();
        scanner.nextLine(); // Boş satırı oku
        PersonDao.deletePerson(personId);
    }
    public static Person login(String username, String password) {
        Person user = PersonDao.getPersonByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    public static Person performLogin(Scanner scanner) {
        System.out.println("Username : ");
        String username = scanner.nextLine();
        System.out.println("Password : ");
        String password = scanner.nextLine();
        return PersonDao.login(username, password);
    }
}
