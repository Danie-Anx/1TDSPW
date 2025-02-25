package br.com.fiap.util;

import java.time.LocalDateTime;

public class Logger {

    public static void log (String message) {
        String now = GeneralUtils.nowDateTimeAsString();
        System.out.println("[" + now + "] " + message);
    }
    public static void log (String message, Object origin) {
        log("[" + origin.getClass().getName() + "] " + message);
    }
}
