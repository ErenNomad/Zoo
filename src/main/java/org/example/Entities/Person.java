package org.example.Entities;

public class Person {
    int id;
    String name;
    String lastname;
    String password;
    String userName;
    String role;
    public Person(String name, String lastname, String password, String userName, String role) {
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.userName = userName;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String  getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUser_name() {
        return userName;
    }
    public void setUser_name(String user_name) {
        this.userName = userName;
    }
}
