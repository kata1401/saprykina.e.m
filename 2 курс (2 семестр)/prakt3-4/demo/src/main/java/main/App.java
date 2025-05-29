package main;

import main.config.DatabaseConfig;
import main.controller.*;
import main.model.Candidate;
import main.model.User;
import main.service.*;
import main.utils.AuditLogger;
import main.utils.H2WebServer;
import main.utils.ValidationUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    private static final AuthService authService = new AuthService();
    private static final VotingService votingService = new VotingService();
    private static final CandidateService candidateService = new CandidateService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        H2WebServer.startWebConsole(); // Запуск H2 Web Console при старте приложения
        initializeDatabase();
        AuditLogger.log("Application started");
        
        while (true) {
            System.out.println("\nДобро пожаловать в систему электронного голосования!");
            System.out.println("1. Войти в личный кабинет");
            System.out.println("2. Зарегистрироваться как новый пользователь");
            System.out.println("3. Завершить работу приложения");
            System.out.print("Ваш выбор: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    AuditLogger.log("Application stopped");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Указан неверный пункт меню. Пожалуйста, попробуйте снова.");
            }
        }
    }
    private static void initializeDatabase() {
        try (Connection conn = DatabaseConfig.getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(new String(
                App.class.getResourceAsStream("/init.sql").readAllBytes()));
        } catch (Exception e) {
            System.err.println("Ошибка инициализации БД: " + e.getMessage());
        }
    }

    private static void login() throws SQLException {
        System.out.print("Введите логин пользователя: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        User user = authService.authenticate(login, password);
        if (user == null) {
            System.out.println("Ошибка входа: неверный логин или пароль.");
            return;
        }

        AuditLogger.log("User logged in: " + user.getLogin());
        showRoleBasedMenu(user);
    }

    private static void register() {
        System.out.println("\n--- Регистрация нового участника голосования ---");
        
        // Ввод данных
        System.out.print("ФИО: ");
        String fullName = scanner.nextLine();
        
        System.out.print("Дата рождения (дд.мм.гггг): ");
        String birthDateStr = scanner.nextLine();
        
        System.out.print("СНИЛС (XXX-XXX-XXX XX): ");
        String snils = scanner.nextLine();
        
        System.out.print("Желаемый логин: ");
        String login = scanner.nextLine();
        
        System.out.print("Придумайте пароль: ");
        String password = scanner.nextLine();

        // Валидация данных
        if (!ValidationUtils.isValidBirthDate(birthDateStr)) {
            System.out.println("Ошибка: введена некорректная дата рождения.");
            return;
        }
        
        if (!ValidationUtils.isValidSNILS(snils)) {
            System.out.println("Ошибка: СНИЛС не соответствует требуемому формату.");
            return;
        }
        
        if (authService.authenticate(login, password) != null) {
            System.out.println("Ошибка: пользователь с таким логином уже зарегистрирован.");
            return;
        }

        try {
            // Создание пользователя
            LocalDate birthDate = LocalDate.parse(birthDateStr, 
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                
            User newUser = new User(login, password, User.Role.USER);
            newUser.setFullName(fullName);
            newUser.setSnils(snils);
            newUser.setBirthDate(birthDate);

            // Сохранение пользователя
            authService.registerUser(newUser);
            System.out.println("Регистрация завершена! Теперь вы можете войти в свой аккаунт.");

        } catch (Exception e) {
            System.out.println("Ошибка при регистрации: " + e.getMessage());
        }
    }

    private static void showRoleBasedMenu(User user) throws SQLException {
        switch (user.getRole()) {
            case ADMIN:
                new AdminController(authService, votingService, candidateService).start();
                break;
            case CEC:
                new CECController(votingService).start();
                break;
            case CANDIDATE:
                Candidate candidate = candidateService.getCandidateByUser(user);
                if (candidate != null) {
                    new CandidateController(candidateService).start(candidate);
                }
                break;
            case USER:
                new UserController(votingService, candidateService).start(user);
                break;
            default:
                throw new IllegalStateException("Неизвестная роль");
        }
    }
}