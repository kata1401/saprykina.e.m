package main.controller;

import main.model.User;
import main.model.Candidate;
import main.service.AuthService;
import main.service.CandidateService;
import main.service.VotingService;
import main.view.AdminView;
import java.util.List;

public class AdminController {
    private final AuthService authService;
    private final CandidateService candidateService;
    private final AdminView view;

    public AdminController(AuthService authService,
                          VotingService votingService,
                          CandidateService candidateService) {
        this.authService = authService;
        this.candidateService = candidateService;
        this.view = new AdminView();
    }

    public void start() {
        boolean running = true;
        while (running) {
            view.showAdminMenu();
            int choice = view.getChoice();
            
            switch (choice) {
                case 1:
                    manageUsers();
                    break;
                case 2:
                    manageCECs();
                    break;
                case 3:
                    manageCandidates();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Ошибка: выбран некорректный пункт меню.");
            }
        }
    }

    private void manageUsers() {
        List<User> users = authService.getAllUsers();
        view.displayUsers(users);
        String login = view.promptForLogin();
        authService.deleteUser(login);
        view.showSuccess("Пользователь был успешно удалён из системы.");
    }

    private void manageCECs() {
        // Реализация управления ЦИК
    }

    private void manageCandidates() {
        List<Candidate> candidates = candidateService.getAllCandidates();
        view.displayCandidates(candidates);
    }
}