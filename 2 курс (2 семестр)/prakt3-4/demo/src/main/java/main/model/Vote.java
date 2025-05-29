package main.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Vote {
    private String userId;
    private UUID votingId;
    private String candidateId;
    private LocalDateTime timestamp;

    public Vote(String userId, UUID votingId, String candidateId) {
        if (userId == null || votingId == null || candidateId == null) {
            throw new IllegalArgumentException("Все параметры должны быть заданы");
        }
        this.userId = userId;
        this.votingId = votingId;
        this.candidateId = candidateId;
        this.timestamp = LocalDateTime.now();
    }

    // Геттеры
    public String getUserId() { return userId; }
    public UUID getVotingId() { return votingId; }
    public String getCandidateId() { return candidateId; }
    public LocalDateTime getTimestamp() { return timestamp; }
}