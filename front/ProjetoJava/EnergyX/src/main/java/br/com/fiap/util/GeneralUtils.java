package br.com.fiap.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneralUtils {

    public static boolean isNullOrBlank(String str) {
        return str == null || str.trim().length() == 0;
    }
    public static boolean isNotNullAndNotBlank(String str) {
        return str != null && str.trim().length() != 0;
    }
    public static String snakeCaseToCamelCase (String input) {
        String[] words = input.split("_");
        for (int i = 1; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            words[i] = String.valueOf(chars);
        }
        return String.join("", words);
    }
    public static String camelCaseToSnakeCase (String input) {
        return input.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toLowerCase();
    }

    public static String getGetterName (String fieldName) {
        char[] chars = fieldName.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return "get" + String.valueOf(chars);
    }

    public static String getSetterName (String fieldName) {
        char[] chars = fieldName.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return "set" + String.valueOf(chars);
    }

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public static String dateTimeToString (LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(formatter);
    }

    public static String nowDateTimeAsString () {
        return dateTimeToString(LocalDateTime.now());
    }
}
