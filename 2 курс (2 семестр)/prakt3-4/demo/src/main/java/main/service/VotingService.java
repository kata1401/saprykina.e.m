package main.service;

import main.model.Voting;
import main.model.Candidate;
import main.model.VoteDAO;
import main.model.Vote;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

import main.model.User;

public class VotingService {
    private final Map<String, Voting> activeVotings = new HashMap<>();
    private final List<Voting> archivedVotings = new ArrayList<>();
    private final Map<String, Set<String>> votedUsers = new HashMap<>();
    private final VoteDAO voteDAO = new VoteDAO();

    public void createVoting(String title, LocalDateTime endDate) {
        Voting voting = new Voting(title, endDate);
        activeVotings.put(voting.getId().toString(), voting);
    }

    public void addCandidateToVoting(String votingId, Candidate candidate) {
        Voting voting = activeVotings.get(votingId);
        if (voting != null) {
            voting.addCandidate(candidate);
        }
    }

    public void archiveVoting(String votingId) {
        Voting voting = activeVotings.remove(votingId);
        if (voting != null) {
            archivedVotings.add(voting);
        }
    }

        public List<Voting> getActiveVotings() {
        return new ArrayList<>(activeVotings.values());
    }
    
    @SuppressWarnings("unlikely-arg-type")
    public Voting getVotingById(String id) {
        return activeVotings.getOrDefault(id, 
            archivedVotings.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(null));
    }
    
    public void addVote(User user, Voting voting, Candidate candidate) throws SQLException {
        if (voting.isActive() && !hasVoted(user, voting)) {
            voteDAO.saveVote(new Vote(user.getId(), voting.getId(), candidate.getId()));
            
            voting.addVote(candidate);
            
            votedUsers.computeIfAbsent(voting.getId().toString(), k -> new HashSet<>()).add(user.getId());
        }
    }

    private boolean hasVoted(User user, Voting voting) {
        if (votedUsers.getOrDefault(voting.getId().toString(), Collections.emptySet()).contains(user.getId())) {
            return true;
        }
        
        try {
            return voteDAO.hasUserVoted(user.getId(), voting.getId().toString());
        } catch (SQLException e) {
            System.err.println("Ошибка проверки голоса: " + e.getMessage());
            return true; // Блокируем голосование при ошибке
        }
    }

    public List<Voting> getVotingHistory(String userId) {
        List<Voting> history = new ArrayList<>();
        activeVotings.values().forEach(v -> {
            if (v.getVoters().contains(userId)) history.add(v);
        });
        archivedVotings.forEach(v -> {
            if (v.getVoters().contains(userId)) history.add(v);
        });
        return history;
    }
}