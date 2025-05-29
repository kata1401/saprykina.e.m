package main.controller;

import main.model.Candidate;
import main.model.Voting;
import main.service.CandidateService;
import main.view.CandidateView;
import java.util.List;
import java.util.Scanner;

public class CandidateController {
    private final CandidateService candidateService;
    private final CandidateView view;
    private final Scanner scanner = new Scanner(System.in);
    private Candidate currentCandidate;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
        this.view = new CandidateView();
    }

    public void updateProfile(String newName, String newBio) {
        if (currentCandidate != null) {
            candidateService.updateCandidate(
                currentCandidate.getId(), 
                newName, 
                newBio
            );
            view.showProfileUpdateSuccess();
        }
    }

    public void viewPreviousResults() {
        List<Voting> participations = candidateService.getParticipations(currentCandidate.getId());
        view.displayPreviousResults(participations);
    }

    public void viewAllParticipations() {
        List<Voting> allParticipations = candidateService.getAllParticipations(currentCandidate.getId());
        view.displayAllParticipations(allParticipations);
    }

    public void start(Candidate candidate) {
        this.currentCandidate = candidate;
        boolean running = true;
        while (running) {
            view.showMenu();
            int choice = view.getUserChoice();
            
            switch (choice) {
                case 1: 
                    updateProfile();
                    break;
                case 2: 
                    viewPreviousResults();
                    break;
                case 3: 
                    viewAllParticipations();
                    break;
                case 0: 
                    running = false;
                    break;
                default: 
                    view.showError("Неверный выбор");
            }
        }
    }

    private void updateProfile() {
        System.out.print("Введите новое имя: ");
        String newName = scanner.nextLine();
        System.out.print("Введите новую биографию: ");
        String newBio = scanner.nextLine();
        updateProfile(newName, newBio);
    }
}