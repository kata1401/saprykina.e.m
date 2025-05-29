package main.view;

import main.model.Voting;
import main.model.Candidate;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("\nДоступные действия для пользователя:");
        System.out.println("1. Принять участие в голосовании");
        System.out.println("2. Ознакомиться с кандидатами");
        System.out.println("3. Просмотреть историю ваших голосований");
        System.out.println("0. Завершить работу");
        System.out.print("Пожалуйста, выберите нужный пункт: ");
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayRegistrationForm() {
        System.out.println("\n--- Форма регистрации нового пользователя ---");
    }

    public void displayError(String message) {
        System.out.println("Внимание! Произошла ошибка: " + message);
    }

    public void displaySuccess(String message) {
        System.out.println("Операция завершена успешно: " + message);
    }

    public void displayInfo(String message) {
        System.out.println("Справка: " + message);
    }

    public void displayVotings(List<Voting> votings) {
        System.out.println("\nТекущие открытые голосования:");
        votings.forEach(v -> System.out.println("ID: " + v.getId() + " | Название: " + v.getTitle()));
    }

    public void displayCandidates(List<Candidate> candidates) {
        System.out.println("\nПеречень кандидатов:");
        candidates.forEach(c -> System.out.println("ID: " + c.getId() + " | Имя: " + c.getName()));
    }

    public void displayVotingHistory(List<Voting> history) {
        System.out.println("\nВаши завершённые и текущие голосования:");
        history.forEach(v -> System.out.println("Голосование: " + v.getTitle() + " | Завершение: " + v.getEndDate()));
    }

    public void promptForRegistration() {
        System.out.println("\n--- Регистрация нового участника ---");
    }

    public void showError(String message) {
        System.out.println("[Ошибка] " + message);
    }

    public void showSuccess(String message) {
        System.out.println("[Успех] " + message);
    }

    public void showVotings(List<Voting> votings) {
        votings.forEach(v -> System.out.println("ID: " + v.getId() + " | Название: " + v.getTitle()));
    }

    public void showCandidates(List<Candidate> candidates) {
        candidates.forEach(c -> System.out.println("ID: " + c.getId() + " | Имя: " + c.getName()));
    }

}