package userinput;
import java.util.Scanner;
import java.util.UUID;

public class UserInput {
    private Scanner scanner;
    /*
    * constructor for userinput class
    *  read input from the console
    */
    public UserInput() {
        scanner = new Scanner(System.in);
    }
    /*
    * Prompts the user for a string input and validates it
    * 
    * @param prompt the message displayed to the user to request input
    * @return the validat string input from the user, or null if the user chooses not to try again
    */
    public String TakeString(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();
        if (Validator.isValidString(input)) {
            return input;
        } else {
            System.out.println("Invalid input. Type 'y' to try again.");
            String tryAgain = scanner.nextLine();
            if (tryAgain.equals("y") || tryAgain.equals("Y")) {
                return TakeString(prompt);
            }
            return null;
        }
    }
    /*
    * prompts the user for a priority input and validates it
    * 
    * @param prompt the message displayed to the user to request input
    * @return the validated priority input as a string, or null if the user chooses not to try again
    */
    public String TakePriority(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();
        if (Validator.isValidPriority(input)) {
            return input;
        } else {
            System.out.println("Invalid priority. Type 'y' to try again.");
            String tryAgain = scanner.nextLine();
            if (tryAgain.equals("Y") || tryAgain.equals("y")) {
                return TakePriority(prompt);
            }
            return null;
        }
    }
    /*
    * Prompts the user for a date input and validates it
    * 
    * @param prompt the message displayed to the user to request input
    * @return the validated date input as a string, or null if the user chooses not to try again
    */
    public String TakeDate(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();
        if (Validator.isValidDate(input)) {
            return input;
        } else {
            System.out.println("Invalid date. Type 'y' to try again.");
            String tryAgain = scanner.nextLine();
            if (tryAgain.equals("Y") || tryAgain.equals("y")) {
                return TakeDate(prompt);
            }
            return null;
        }
    }
    /*
    * Prompts the user for an ID input and validates it
    * 
    * @param prompt the message displayed to the user to request input
    * @return the validated ID as a UUID object, or null if the user chooses not to try again
    */
    public UUID TakeId(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();
        if (Validator.isValidId(input)) {
            return UUID.fromString(input);
        } else {
            System.out.println("Invalid id. Type 'y' to try again.");
            String tryAgain = scanner.nextLine();
            if (tryAgain.equals("Y") || tryAgain.equals("y")) {
                return TakeId(prompt);
            }
            return null;
        }
    }
}
