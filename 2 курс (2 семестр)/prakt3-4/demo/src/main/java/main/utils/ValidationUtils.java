package main.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class ValidationUtils {
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("dd.MM.uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    public static boolean isValidSNILS(String snils) {
        return snils != null && snils.matches("^\\d{3}-\\d{3}-\\d{3} \\d{2}$");
    }

    public static boolean isValidBirthDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, DATE_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }
}