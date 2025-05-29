package main.view;

import java.util.Scanner;

public class CECView {
    private final Scanner scanner = new Scanner(System.in);

    public void showCECMenu() {
        System.out.println("\nМеню Центральной избирательной комиссии:");
        System.out.println("1. Создать новое голосование");
        System.out.println("2. Добавить кандидата в голосование");
        System.out.println("3. Просмотреть итоги голосования");
        System.out.println("4. Сохранить результаты в PDF-файл");
        System.out.println("0. Завершить работу");
        System.out.print("Выберите нужную опцию: ");
    }

    public String promptForVotingId() {
        System.out.print("\nПожалуйста, введите идентификатор голосования: ");
        return scanner.nextLine();
    }

    public String promptForTitle() {
        System.out.print("Укажите название нового голосования: ");
        return scanner.nextLine();
    }

    public String promptForEndDate() {
        System.out.print("Введите дату и время окончания (ГГГГ-ММ-ДДЧЧ:ММ): ");
        return scanner.nextLine();
    }

    public String promptForCandidateId() {
        System.out.print("Укажите идентификатор кандидата: ");
        return scanner.nextLine();
    }

    public String promptForCandidateName() {
        System.out.print("Введите полное имя кандидата: ");
        return scanner.nextLine();
    }

    public String promptForCandidateBio() {
        System.out.print("Добавьте краткую биографию кандидата: ");
        return scanner.nextLine();
    }

    public String promptForFilePath(String defaultPath) {
        System.out.printf("Укажите путь для сохранения файла [%s]: ", defaultPath);
        return scanner.nextLine();
    }

    public void showError(String message) {
        System.out.println("✗ Произошла ошибка: " + message);
    }

    public void showSuccess(String message) {
        System.out.println("✓ Операция выполнена: " + message);
    }

    public int getChoice() {
        return scanner.nextInt();
    }

    public void clearBuffer() {
        scanner.nextLine();
    }
}