package userinput;
import  java.time.LocalDate;
import java.util.UUID;

public class Validator {
    public static boolean isValidPriority(String priority) {
        try {
            int priorityInt = Integer.parseInt(priority);
            return priorityInt >= 1 && priorityInt <= 5;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidString(String str) {
        return str != null && !(str.trim().isEmpty());
    }

    public static boolean isValidId(String id) {
        try {
            UUID.fromString(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
