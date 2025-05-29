package main.model;

import java.time.LocalDate;
import java.util.UUID;

public class User {
    public enum Role { ADMIN, CEC, CANDIDATE, USER }
    
    private String id;
    private String login;
    private String password;
    private Role role;
    private String fullName;
    private String snils;
    private LocalDate birthDate;

    public User(String login, String password, Role role) {
        this.id = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        this.role = role;
    }

    // Геттеры
    public String getId() { return id; }
    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
    public String getFullName() { return fullName; }
    public String getSnils() { return snils; }
    public LocalDate getBirthDate() { return birthDate; }

    // Сеттеры
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setSnils(String snils) { this.snils = snils; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
}