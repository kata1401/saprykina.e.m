package main.service;

import main.model.Candidate;
import main.model.User;
import main.model.Voting;
import java.util.*;

public class CandidateService {
    private final Map<String, Candidate> candidates = new HashMap<>();
    private final Map<String, List<Voting>> participations = new HashMap<>();

    public void createCandidate(String id, String name, String bio) {
        Candidate candidate = new Candidate(id, name, bio);
        candidates.put(id, candidate);
        participations.put(id, new ArrayList<>());
    }
    
    public void updateCandidate(String id, String name, String bio) {
        if (candidates.containsKey(id)) {
            Candidate candidate = candidates.get(id);
            candidate.setName(name);
            candidate.setBiography(bio);
        }
    }

    public void addParticipation(String candidateId, Voting voting) {
        participations.computeIfAbsent(candidateId, k -> new ArrayList<>()).add(voting);
    }

    public List<Voting> getParticipations(String candidateId) {
        return participations.getOrDefault(candidateId, Collections.emptyList());
    }

    public Map<String, List<Voting>> getAllParticipations() {
        return new HashMap<>(participations);
    }

    public List<Candidate> getAllCandidates() {
        return new ArrayList<>(candidates.values());
    }

    public Optional<Candidate> getCandidateById(String id) {
        return Optional.ofNullable(candidates.get(id));
    }

    public List<Voting> getAllParticipations(String candidateId) {
        return participations.getOrDefault(candidateId, Collections.emptyList());
    }

    public Candidate getCandidateByUser(User user) {
        return candidates.values().stream()
            .filter(c -> c.getUserId().equals(user.getId()))
            .findFirst()
            .orElse(null);
    }
}