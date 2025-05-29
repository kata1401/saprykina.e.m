package main.model;

import java.util.ArrayList;
import java.util.List;

public class Candidate {
    private String id;
    private String name;
    private String biography;
    private int votesCount = 0; // Добавлено поле
    private List<Voting> participationHistory = new ArrayList<>();
    private String userId;

    // Конструкторы
    public Candidate(String id, String name, String biography) {
        this.id = id;
        this.name = name;
        this.biography = biography;
    }

    public Candidate(String id, String name, String bio, String userId) {
        this(id, name, bio);
        this.userId = userId;
    }
    
    // Методы для работы с голосами
    public void incrementVotes() {
        votesCount++;
    }
    
    public void resetVotes() {
        votesCount = 0;
    }
    
    // Геттеры
    public String getId() { return id; }
    public String getName() { return name; }
    public String getBiography() { return biography; }
    public int getVotesCount() { return votesCount; }
    public List<Voting> getParticipationHistory() { return participationHistory; }
    public String getUserId() { return userId; }

    // Сеттеры
    public void setName(String name) { this.name = name; }
    public void setBiography(String biography) { this.biography = biography; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public void addParticipation(Voting voting) {
        participationHistory.add(voting);
    }
}