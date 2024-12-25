package models;
import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

/**
 * Represents a task in a to-do list application.
 * Each task contains a unique ID, title, description, due date, creation date, priority, and status (complete or incomplete).
 */
public class Task {
   
    private UUID id;
    private String title;
    private String description;
    private String dueDate;
    private String creationDate;
    private int priority; 
    private boolean isComplete;
    /*
     *Constructs a new Task instance.
     * 
     * @param title:The title of the task. Must not be null or empty.
     * @param description :description The description of the task. Must not be null or empty.
     * @param dueDate :The due date of the task in the format "yyyy-MM-dd". Must be a valid date.
     * @param priority :The priority level of the task (1 to 5). Must fall within the specified range.
     * @throws IllegalArgumentException if project, collaborators, or client are null or invalid.
     */


    public Task(String title, String description, String dueDate, int priority) {
        this.id = UUID.randomUUID();
        this.isComplete = false;
        this.creationDate = LocalDate.now().toString(); 
        setDescription(description);
        setTitle(title);
        setDueDate(dueDate); 
        setPriority(priority);
    }
    /* 
     * Gets the unique ID of the task.
     * @return The UUID of the task.
     */
    public UUID getId() {
        return id;
    }
    /*
     * Gets the description of the task.
     * 
     * @return The description of the task.
     */

    public String getDescription() {
        return description;
    }
   /*
     * Sets the description of the task. The description must not be null or empty.
     * 
     * @param description The description to set.
     * @throws IllegalArgumentException If the description is null or empty.
     */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description;
    }
   /*
     * Checks if the task is marked as complete.
     * 
     * @return true if the task is complete, false otherwise.
     */
    public boolean isComplete() {
        return isComplete;
    }
   /*
     * Marks the task as complete.
     */
    public void markComplete() {
        this.isComplete = true;
    }
     /*
     * Marks the task as incomplete.
     */
    public void markIncomplete() {
        this.isComplete = false;
    }

    /*
     * Gets the title of the task.
     * 
     * @return The title of the task.
     */

    public String getTitle() {
        return title;
    }
 /*
     * Sets the title of the task. The title must not be null or empty.
     * 
     * @param title The title to set.
     * @throws IllegalArgumentException If the title is null or empty.
     */
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
    }
 /*
     * Gets the due date of the task.
     * 
     * @return The due date of the task in yyyy-MM-dd format.
     */
    public String getDueDate() {
        return dueDate;
    }
  /*
     * Sets the due date of the task. The due date must be in yyyy-MM-dd format.
     * 
     * @param dueDate The due date to set.
     * @throws IllegalArgumentException If the due date is null or in an invalid format.
     */
    public void setDueDate(String dueDate) {
        if (dueDate == null) {
            throw new IllegalArgumentException("Due date cannot be null");
        }
        try {
            LocalDate.parse(dueDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Please use the format yyyy-mm-dd");
        }
        this.dueDate = dueDate;
    }
    /*
     * Get the priority of the task.
     * 
     * @return The priority of the task (1-5).
     */
    public int getPriority() {
        return priority;
    }
    /*
     * Set the priority of the task.
     * @param priority the priority of the task.
     * @return void.
     */
    public void setPriority(int priority) {
        if(priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5");
        }
        this.priority = priority;
    }
    /*
     * Get the creation date of the task.
     * @return the creation date of the task.
     */
    public String getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" + "Title: " + title + "\n" + "Description: " + description + "\n" + "Due Date: " + dueDate + "\n" + "Priority: " + priority + "\n" + "Creation Date: " + creationDate + "\n" + "Is Complete: " + isComplete + "\n";
    }
}
package models;
import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

public class Task {
    private UUID id;
    private String title;
    private String description;
    private String dueDate;
    private String creationDate;
    private int priority;
    private boolean isComplete;

    public Task(String title, String description, String dueDate, int priority) {
        this.id = UUID.randomUUID();
        this.isComplete = false;
        this.creationDate = LocalDate.now().toString();
        setDescription(description);
        setTitle(title);
        setDueDate(dueDate);
        setPriority(priority);
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
/* ""
 * hhghg \n
 * 
 */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void markComplete() {
        this.isComplete = true;
    }

    public void markIncomplete() {
        this.isComplete = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }
/* 2024-12-12 */
    public void setDueDate(String dueDate) {
        if (dueDate == null) {
            throw new IllegalArgumentException("Due date cannot be null");
        }
        try {
            LocalDate.parse(dueDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Please use the format yyyy-mm-dd");
        }
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }
    /*
     * Set the priority of the task
     * @param priority the priority of the task
     * @return void
     */
    public void setPriority(int priority) {
        if(priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5");
        }
        this.priority = priority;
    }
    /*
     * Get the creation date of the task
     * @return the creation date of the task
     */
    public String getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" + "Title: " + title + "\n" + "Description: " + description + "\n" + "Due Date: " + dueDate + "\n" + "Priority: " + priority + "\n" + "Creation Date: " + creationDate + "\n" + "Is Complete: " + isComplete + "\n";
    }
}
