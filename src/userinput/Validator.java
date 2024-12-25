package userinput;
import  java.time.LocalDate;
import java.util.UUID;

public class Validator {

    /*
     * Validates the priority input
     * @param priority the priority input to validate
     * @return true if the priority is valid, false otherwise
     */
    public static boolean isValidPriority(String priority) {
        try {
            int priorityInt = Integer.parseInt(priority);
            return priorityInt >= 1 && priorityInt <= 5;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
     * Validates the date input
     * @param date the date input to validate
     * @return true if the date is valid, false otherwise
     */
    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
     * Validates the string input
     * @param str the string input to validate
     * @return true if the string is valid, false otherwise
     */
    public static boolean isValidString(String str) {
        return str != null && !(str.trim().isEmpty());
    }

    /*
     * Validates the id input
     * @param id the id input to validate
     * @return true if the id is valid, false otherwise
     */
    public static boolean isValidId(String id) {
        try {
            UUID.fromString(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
