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
