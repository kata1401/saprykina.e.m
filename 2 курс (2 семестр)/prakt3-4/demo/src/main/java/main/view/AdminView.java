package main.view;

import java.util.List;
import java.util.Scanner;
import main.model.User;
import main.model.Candidate;

public class AdminView {
    private final Scanner scanner = new Scanner(System.in);

    public void showAdminMenu() {
        System.out.println("\nПанель администратора системы:");
        System.out.println("1. Просмотр и управление пользователями");
        System.out.println("2. Управление учетными записями ЦИК");
        System.out.println("3. Просмотр и редактирование кандидатов");
        System.out.println("0. Выйти из панели администратора");
        System.out.print("Выберите действие для продолжения: ");
    }

    public int getChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void displayUsers(List<User> users) {
        System.out.println("\nТекущий список пользователей системы:");
        users.forEach(u -> System.out.println("Логин: " + u.getLogin() + " | Роль: " + u.getRole()));
    }

    public String promptForLogin() {
        System.out.print("\nВведите логин пользователя для удаления: ");
        return scanner.nextLine();
    }

    public void showSuccess(String message) {
        System.out.println("Операция администратора завершена: " + message);
    }

    public void displayCandidates(List<Candidate> candidates) {
        System.out.println("\nАктуальный список кандидатов:");
        candidates.forEach(c -> System.out.println("ID: " + c.getId() + " | Имя: " + c.getName()));
    }
}