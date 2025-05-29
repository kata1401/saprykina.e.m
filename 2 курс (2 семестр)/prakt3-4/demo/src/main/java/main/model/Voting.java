package main.model;

import java.time.LocalDateTime;
import java.util.*;

public class Voting {
    private UUID id;
    private String title;
    private LocalDateTime endDate;
    private Map<Candidate, Integer> results = new HashMap<>();
    private boolean isActive;
    private List<Candidate> candidates = new ArrayList<>();

    public Voting(String title, LocalDateTime endDate) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.endDate = endDate;
        this.isActive = true;
    }

    // Геттеры
    public UUID getId() { return id; }
    public String getName() { return title; }
    public Map<Candidate, Integer> getResults() { return new HashMap<>(results); }
    public LocalDateTime getEndDate() { return endDate; }
    public LocalDateTime getDate() { return endDate; }
    public boolean isActive() { return isActive && LocalDateTime.now().isBefore(endDate); }
    public String getStatus() { return isActive() ? "Active" : "Closed"; }
    private Set<String> voters = new HashSet<>();
    public String getTitle() {
        return this.title;
    }

    public List<Candidate> getCandidates() {
        return Collections.unmodifiableList(candidates);
    }

    public int getTotalVotes() {
        return results.values().stream()
            .mapToInt(Integer::intValue)
            .sum();
    }

    public void addVoter(String userId) {
        voters.add(userId);
    }
    
    public Set<String> getVoters() {
        return Collections.unmodifiableSet(voters);
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
        results.put(candidate, 0);
    }

    public void addVote(Candidate candidate) {
        if (isActive() && candidates.contains(candidate)) {
            results.put(candidate, results.get(candidate) + 1);
        }
    }

    public void closeVoting() {
        isActive = false;
    }
}