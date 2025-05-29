package main.utils;

import org.h2.tools.Server;

public class H2WebServer {
    public static void startWebConsole() {
        try {
            // Важно: для доступа к in-memory базе из консоли H2
            // приложение должно быть запущено и база создана в том же процессе JVM!
            Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            System.out.println("H2 Web Console доступна по адресу: http://localhost:8082");
            System.out.println("Для доступа к вашей in-memory базе используйте JDBC URL: jdbc:h2:mem:voting");
            System.out.println("User Name: sa (пароль оставьте пустым)");
            System.out.println("Внимание: база будет доступна только пока работает это приложение!");
        } catch (Exception e) {
            System.err.println("Не удалось запустить H2 Web Console: " + e.getMessage());
        }
    }
}
