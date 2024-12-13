package models;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

public class TodoList {
    private UUID id;
    private String title;
    private String description;
    private LocalDate creationDate;
    private List<Task> tasks;

    public TodoList(String title, String description) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.tasks = new ArrayList<>();
        this.description = description;
        this.creationDate = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean removeTask(UUID taskId) {
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                tasks.remove(task);
                return true;
            }
        }
        return false;
    }

    public Task getTask(UUID taskId) {
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks in TodoList \"" + title + "\":");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    public void listTasks(int priority) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks in TodoList \"" + title + "\" with priority " + priority + ":");
            for (Task task : tasks) {
                if (task.getPriority() == priority) {
                    System.out.println(task);
                }
            }
        }
    }

    public void listTasks(boolean isComplete) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            String status = isComplete ? "complete" : "incomplete";
            System.out.println("Tasks in TodoList \"" + title + "\" that are " + status + ":");
            for (Task task : tasks) {
                if (task.isComplete() == isComplete) {
                    System.out.println(task);
                }
            }
        }
    }

    public void listTasks(LocalDate dueDate) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks in TodoList \"" + title + "\" due on " + dueDate + ":");
            for (Task task : tasks) {
                if (task.getDueDate().equals(dueDate)) {
                    System.out.println(task);
                }
            }
        }
    }

    public void markTaskIncomplete(UUID taskId) {
        Task task = getTask(taskId);
        if (task != null) {
            task.markIncomplete();
            System.out.println("Task marked incomplete: " + task.getTitle());
        } else {
            System.out.println("Task not found!");
        }
    }

    public void markTaskComplete(UUID taskId) {
        Task task = getTask(taskId);
        if (task != null) {
            task.markComplete();
            System.out.println("Task marked complete: " + task.getTitle());
        } else {
            System.out.println("Task not found!");
        }
    }

    @Override
    public String toString() {
        return "TodoList [id=" + id + ", title=" + title + "]";
    }
}
