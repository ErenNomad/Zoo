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
                    System.out.println("Giriş başarılı!");
                } else {
                    System.out.println("Kullanıcı girişi başarısız. Tekrar deneyin.");
                }
            }

            if (loggedInUser != null) {
                loggedInUser = mainMenu(loggedInUser, scanner);
                if (loggedInUser == null) {
                    System.out.println("Oturum kapatıldı.");
                }
            }
        }
    }
    public static Person mainMenu(Person loggedInUser, Scanner scanner) {
        System.out.println("----- Ana Menü -----");
        System.out.println("1. Kullanıcı İşlemleri");
        System.out.println("2. Hayvan İşlemleri");
        System.out.println("3. Oturumu Kapat");
        System.out.println("4. Çıkış");
        System.out.print("Seçiminizi yapın: ");
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Boş satırı oku

            switch (choice) {
                case 1 -> userMenu(loggedInUser, scanner);
                case 2 -> animalMenu(loggedInUser, scanner);
                case 3 -> {return null;} // Oturumu kapat
                case 4 -> {
                    System.out.println("Çıkış yapılıyor...");
                    System.exit(0); // Programı sonlandırır
                }
                default -> System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Geçersiz giriş. Lütfen sayı girin.");
            scanner.nextLine(); // Geçersiz girişi temizle
        }
        return loggedInUser;
    }
    //kullanıcı işlemleri menüsü
    public static void userMenu(Person loggedInUser, Scanner scanner) {
        if (loggedInUser.getRole().equals("ADMIN")) {
            boolean userMenuLoop = true;
            while (userMenuLoop) {
                System.out.println("----- Kullanıcı İşlemleri -----");
                System.out.println("1. Kullanıcıları Görüntüle");
                System.out.println("2. Kullanıcı Ekle");
                System.out.println("3. Kullanıcı Sil");
                System.out.println("4. Geri Dön");
                System.out.print("Seçiminizi yapın: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                switch (choice) {
                    case 1 -> PersonDao.viewTable();
                    case 2 -> PersonDao.addUser(scanner);
                    case 3 -> PersonDao.deleteUser(scanner);
                    case 4 -> {
                        userMenuLoop = false;
                        System.out.println("Ana menüye dönülüyor...");
                    }
                    default -> System.out.println("Geçersiz seçim. Tekrar deneyin.");
                }
                System.out.println();
            }
        } else {
            System.out.println("Bu menüye erişim izniniz bulunmamaktadır.");
        }
    }
    //hayvanlarla ilgili menü
    public static void animalMenu(Person loggedInUser, Scanner scanner) {
        boolean animalMenuLoop = true;
        while (animalMenuLoop) {
            System.out.println("----- Hayvan İşlemleri -----");
            System.out.println("1. Hayvanları Görüntüle");
            System.out.println("2. Hayvan Ekle");
            System.out.println("3. Hayvandan Sesi çıkar");
            System.out.println("4. Geri Dön");
            System.out.print("Seçiminizi yapın: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> AnimalDao.viewTable();
                case 2 -> {
                    if (loggedInUser.getRole().equals("ADMIN")) {
                        AnimalDao.addAnimal(scanner);
                    } else {
                        System.out.println("Bu işlem için yetkiniz bulunmamaktadır.");
                    }
                }
                case 3 -> AnimalDao.animalMakeNoise(scanner);
                case 4 -> {
                    animalMenuLoop = false;
                    System.out.println("Ana menüye dönülüyor...");
                }
                default -> System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
            System.out.println();
        }
    }
}