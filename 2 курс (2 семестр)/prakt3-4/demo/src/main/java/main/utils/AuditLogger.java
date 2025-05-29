package main.utils;

import java.io.IOException;
import java.util.logging.*;

public class AuditLogger {
    private static final Logger logger = Logger.getLogger(AuditLogger.class.getName());

    static {
        try {
            FileHandler fh = new FileHandler("audit.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String message) {
        logger.info(message);
    }
}