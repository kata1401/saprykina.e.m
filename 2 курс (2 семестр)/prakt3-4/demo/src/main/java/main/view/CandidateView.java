package main.view;

import main.model.Voting;
import java.util.List;
import java.util.Scanner;

public class CandidateView {
    private final Scanner scanner = new Scanner(System.in);
    public void showMenu() {
        System.out.println("\nПанель управления кандидата:");
        System.out.println("1. Изменить данные профиля");
        System.out.println("2. Посмотреть результаты прошедших голосований");
        System.out.println("3. Просмотреть все свои участия");
        System.out.println("0. Выйти из аккаунта");
        System.out.print("Выберите необходимое действие: ");
    }

    public void displayPreviousResults(List<Voting> votings) {
        System.out.println("\nРезультаты по завершённым голосованиям:");
        for (Voting v : votings) {
            System.out.println("Голосование: " + v.getTitle() + " | Получено голосов: " + v.getTotalVotes());
        }
    }

    public void displayAllParticipations(List<Voting> participations) {
        System.out.println("\nСписок всех голосований с вашим участием:");
        for (Voting v : participations) {
            System.out.println("Голосование: " + v.getTitle() + " | Статус: " + (v.isActive() ? "Открыто" : "Закрыто"));
        }
    }

    public void showProfileUpdateSuccess() {
        System.out.println("Данные профиля были успешно обновлены.");
    }

    public int getUserChoice() {
        return scanner.nextInt();
    }

    public void showError(String message) {
        System.out.println("Ошибка выполнения: " + message);
    }
}