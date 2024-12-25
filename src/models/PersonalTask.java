package models;
import java.time.LocalDate;
import java.util.List;

/*
 * The PersonalTask class extends the Task class to represent a personal task.
 * It includes additional attributes such as category and location.
 * 
 * This class provides getters and setters for these attributes, 
 * with validation to ensure valid values are provided.
 */
public class PersonalTask extends Task {
    private String category;
    private String location;

    /*
     * Constructor for the PersonalTask class.
     * @param title       The title of the task (inherited from Task).
     * @param description The description of the task (inherited from Task).
     * @param dueDate     The due date of the task in String format (inherited from Task).
     * @param priority    The priority level of the task (inherited from Task).
     * @param category    The category of the personal task.
     * @param location    The location associated with the task.
     * @throws IllegalArgumentException if category or location are null or empty.
     */
    public PersonalTask(String title, String description, String dueDate, int priority, String category, String location) {
        super(title, description, dueDate, priority);
        setCategory(category);
        setLocation(location);
    }

    /*
     * Retrieves the category of the personal task.
     * @return The category of the task.
     */
    public String getCategory() {
        return category;
    }


    /*
     * Sets the category for the personal task.
     * @param category The category of the task.
     * @throws IllegalArgumentException if category is null or empty.
     */
    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }
        this.category = category;
    }

    /*
     * Retrieves the location of the personal task.
     * @return The location of the task.
     */
    
    public String getLocation() {
        return location;
    }


    /*
     * Sets the location for the personal task.
     * @param location The location associated with the task.
     * @throws IllegalArgumentException if location is null or empty.
     */
    public void setLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty");
        }
        this.location = location;
    }


    /*
     * Returns a string representation of the PersonalTask object, 
     * including details from the parent Task class.
     * @return A formatted string with task details.
     */
    @Override
    public String toString() {
        return super.toString() + "Category: " + category + "\n" + "Location: " + location + "\n";
    }
    
}