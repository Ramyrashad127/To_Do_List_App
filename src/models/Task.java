package models;
import java.util.UUID;
import java.time.LocalDate;

public class Task {
    private UUID id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private LocalDate creationDate;
    private int priority;
    private boolean isComplete;

    public Task(String title, String description, LocalDate dueDate, int priority) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.isComplete = false;
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
        this.creationDate = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if(priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5");
        }
        this.priority = priority;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "Task [ID=" + id + ", Title=" + title + ", Description=" + description + ", Due Date=" + dueDate + ", Priority=" + priority + ", Complete=" + isComplete + "]";
    }
}
