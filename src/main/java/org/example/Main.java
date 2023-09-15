package org.example;
import org.example.Entities.Person;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.PersonDao.performLogin;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //login section
        Person loggedInUser = null;
        while (true) {
            if (loggedInUser == null) {
                loggedInUser = performLogin(scanner);
                if (loggedInUser != null) {
                    System.out.println("Login successful!");
                } else {
                    System.out.println("User login failed. Try again.");
                }
            }

            if (loggedInUser != null) {
                loggedInUser = mainMenu(loggedInUser, scanner);
                if (loggedInUser == null) {
                    System.out.println("The session is closed.");
                }
            }
        }
    }
    public static Person mainMenu(Person loggedInUser, Scanner scanner) {
        System.out.println("----- Main Menu -----");
        System.out.println("1. User operations");
        System.out.println("2. Animal operations");
        System.out.println("3. Sign out");
        System.out.println("4. Exit the program");
        System.out.print("Make your choice : ");
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> userMenu(loggedInUser, scanner);
                case 2 -> animalMenu(loggedInUser, scanner);
                case 3 -> {return null;}
                case 4 -> {
                    System.out.println("Checking out...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid selection. Try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid login. Please enter number.");
            scanner.nextLine();
        }
        return loggedInUser;
    }
    public static void userMenu(Person loggedInUser, Scanner scanner) {
        if (loggedInUser.getRole().equals("ADMIN")) {
            boolean userMenuLoop = true;
            while (userMenuLoop) {
                System.out.println("----- User Operations -----");
                System.out.println("1. View Users");
                System.out.println("2. Add User");
                System.out.println("3. Delete User");
                System.out.println("4. Turn back");
                System.out.print("Make your choice  : ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                switch (choice) {
                    case 1 -> PersonDao.viewTable();
                    case 2 -> PersonDao.addUser(scanner);
                    case 3 -> PersonDao.deleteUser(scanner);
                    case 4 -> {
                        userMenuLoop = false;
                        System.out.println("Returning to the main menu...");
                    }
                    default -> System.out.println("Invalid selection. Try again.");
                }
                System.out.println();
            }
        } else {
            System.out.println("You do not have permission to access this menu.");
        }
    }
    public static void animalMenu(Person loggedInUser, Scanner scanner) {
        boolean animalMenuLoop = true;
        while (animalMenuLoop) {
            System.out.println("----- Animal Operations -----");
            System.out.println("1. View Animals");
            System.out.println("2. Add Animal");
            System.out.println("3. Make a Sound from an Animal");
            System.out.println("4. Turn Back");
            System.out.print("Make your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> AnimalDao.viewTable();
                case 2 -> {
                    if (loggedInUser.getRole().equals("ADMIN")) {
                        AnimalDao.addAnimal(scanner);
                    } else {
                        System.out.println("You are not authorized for this operation.");
                    }
                }
                case 3 -> AnimalDao.animalMakeNoise(scanner);
                case 4 -> {
                    animalMenuLoop = false;
                    System.out.println("Returning to the main menu...");
                }
                default -> System.out.println("Invalid selection. Try again.");
            }
            System.out.println();
        }
    }
}