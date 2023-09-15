package org.example.Entities;

public class Visitor extends Person{
    public Visitor(int id,String name, String lastname, String password, String userName, String role) {
        super(name, lastname, password, userName, role);
    }
    public static void look(){
        System.out.println("animals are cared for");
    };
}
