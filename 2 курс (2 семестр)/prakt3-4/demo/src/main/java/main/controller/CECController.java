package main.controller;

import main.model.Voting;
import main.service.VotingService;
import main.view.CECView;
import main.model.Candidate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import main.utils.PDFExporter;

public class CECController {
    private final VotingService votingService;
    private final CECView view;
    private final Scanner scanner = new Scanner(System.in);

    public CECController(VotingService votingService) {
        this.votingService = votingService;
        this.view = new CECView();
    }

    public void start() {
        while (true) {
            view.showCECMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    createVoting();
                    break;
                case 2:
                    addCandidate();
                    break;
                case 3:
                    printResults();
                    break;
                case 0:
                    return;
                default:
                    view.showError("Выбран несуществующий пункт меню. Повторите попытку.");
            }
        }
    }

    private void createVoting() {
        view.promptForTitle();
        String title = scanner.nextLine();
        LocalDateTime endDate = LocalDateTime.now().plusDays(7); // Пример: голосование на 7 дней
        votingService.createVoting(title, endDate);
        view.showSuccess("Новое голосование успешно создано.");
    }

    private void addCandidate() {
        view.promptForVotingId();
        String votingId = scanner.nextLine();
        
        System.out.print("Введите идентификатор кандидата: ");
        String id = scanner.nextLine();
        System.out.print("Укажите имя кандидата: ");
        String name = scanner.nextLine();
        System.out.print("Добавьте биографию кандидата: ");
        String bio = scanner.nextLine();
        
        Candidate candidate = new Candidate(id, name, bio);
        candidate.setUserId("N/A");
        votingService.addCandidateToVoting(votingId, candidate);
        view.showSuccess("Кандидат был добавлен в голосование.");
    }

    private void printResults() {
        view.promptForVotingId();
        String votingId = scanner.nextLine();
        
        Voting voting = votingService.getVotingById(votingId);
        if (voting == null) {
            view.showError("Голосование с указанным идентификатором не найдено.");
            return;
        }
        
        try {
            String defaultPath = "results_" + votingId + ".pdf";
            view.promptForFilePath(defaultPath);
            String path = scanner.nextLine();
            path = path.isEmpty() ? defaultPath : path;
            
            PDFExporter.exportResults(voting, path);
            view.showSuccess("Результаты голосования сохранены в файл: " + path);
        } catch (IOException e) {
            view.showError("Не удалось экспортировать результаты: " + e.getMessage());
        }
    }
}