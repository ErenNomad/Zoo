package org.example.Entities;

public class PetSitter extends Person{

    public PetSitter(int id, String name, String lastname, String password, String userName, String role) {
        super(name, lastname, password, userName, role);
    }
    public static void Feeding(){
        System.out.println("Hayvan besleniyor");
    };
}
