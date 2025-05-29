package main.model;

import java.sql.*;
import main.config.DatabaseConfig;

public class VoteDAO {
    public void saveVote(Vote vote) throws SQLException {
        String sql = "INSERT INTO votes (user_id, voting_id, candidate_id, timestamp) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vote.getUserId());
            stmt.setString(2, vote.getVotingId().toString()); // Конвертация UUID в String
            stmt.setString(3, vote.getCandidateId());
            stmt.setTimestamp(4, Timestamp.valueOf(vote.getTimestamp()));
            stmt.executeUpdate();
        }
    }
    public boolean hasUserVoted(String userId, String votingId) throws SQLException {
        String sql = "SELECT 1 FROM votes WHERE user_id = ? AND voting_id = ? LIMIT 1";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setString(2, votingId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}