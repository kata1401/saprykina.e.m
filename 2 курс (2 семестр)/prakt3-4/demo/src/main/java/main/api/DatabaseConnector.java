package main.api;

import main.model.Voting;
import java.util.*;

public class DatabaseConnector {
    private Map<String, Voting> votings = new HashMap<>();
    
    public void saveVoting(Voting voting) {
        votings.put(voting.getId().toString(), voting);
    }
    
    public Voting loadVoting(String id) {
        return votings.get(id);
    }
}