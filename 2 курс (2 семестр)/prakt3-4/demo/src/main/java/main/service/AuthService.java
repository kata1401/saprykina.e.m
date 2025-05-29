package main.service;

import main.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthService {
    private static final Map<String, User> users = new HashMap<>();
    
    static {
        users.put("admin", new User("admin", "admin123", User.Role.ADMIN));
    }

    public User authenticate(String login, String password) {
        User user = users.get(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    public void registerUser(User user) {
        if (users.containsKey(user.getLogin())) {
            throw new IllegalArgumentException("Пользователь уже существует");
        }
        users.put(user.getLogin(), user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public void deleteUser(String login) {
        users.remove(login);
    }
}