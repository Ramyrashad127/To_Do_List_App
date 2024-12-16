package models;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

public class TodoList {
    private UUID id;
    private String title;
    private String description;
    private String creationDate;
    private List<PersonalTask> personalTasks;
    private List<WorkTask> workTasks;

    public TodoList(String title, String description) {
        this.id = UUID.randomUUID();
        this.creationDate = LocalDate.now().toString();
        this.personalTasks = new ArrayList<>();
        this.workTasks = new ArrayList<>();
        setTitle(title);
        setDescription(description);
    }

    public UUID getId() {
        return id;
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

    public String getCreationDate() {
        return creationDate;
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

    public void addPersonalTask(PersonalTask task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        personalTasks.add(task);
    }

    public void addWorkTask(WorkTask task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        workTasks.add(task);
    }

    public boolean removeTask(UUID taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }
        
        for (PersonalTask task : personalTasks) {
            if (task.getId().equals(taskId)) {
                personalTasks.remove(task);
                return true;
            }
        }
        for (WorkTask task : workTasks) {
            if (task.getId().equals(taskId)) {
                workTasks.remove(task);
                return true;
            }
        }
        return false;
    }

    public PersonalTask getPersonalTask(UUID taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }

        for (PersonalTask task : personalTasks) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }

    public WorkTask getWorkTask(UUID taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }

        for (WorkTask task : workTasks) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }

    public void listTasks() {
        if (personalTasks.isEmpty() && workTasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks in TodoList \"" + title + "\":");
            for (PersonalTask task : personalTasks) {
                System.out.println(task);
            }
            for (WorkTask task : workTasks) {
                System.out.println(task);
            }
        }
    }

    public void listTasks(int priority) {
        if (personalTasks.isEmpty() && workTasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks in TodoList \"" + title + "\" with priority " + priority + ":");
            for (PersonalTask task : personalTasks) {
                if (task.getPriority() == priority) {
                    System.out.println(task);
                }
            }
            for (WorkTask task : workTasks) {
                if (task.getPriority() == priority) {
                    System.out.println(task);
                }
            }
        }
    }

    public void listTasks(boolean isComplete) {
        if (personalTasks.isEmpty() && workTasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks in TodoList \"" + title + "\" that are " + (isComplete ? "complete" : "incomplete") + ":");
            for (PersonalTask task : personalTasks) {
                if (task.isComplete() == isComplete) {
                    System.out.println(task);
                }
            }
            for (WorkTask task : workTasks) {
                if (task.isComplete() == isComplete) {
                    System.out.println(task);
                }
            }
        }
    }

    /*
     * 
     */
    public void listTasks(LocalDate dueDate) {
        if (dueDate == null) {
            throw new IllegalArgumentException("Due date cannot be null");
        }

        if (personalTasks.isEmpty() && workTasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks in TodoList \"" + title + "\" due on " + dueDate + ":");
            for (PersonalTask task : personalTasks) {
                if (task.getDueDate().equals(dueDate)) {
                    System.out.println(task);
                }
            }
            for (WorkTask task : workTasks) {
                if (task.getDueDate().equals(dueDate)) {
                    System.out.println(task);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "id:" + id + "\n" + "TodoList: " + title + "\n" + "Description: " + description + "\n" + "Creation Date: " + creationDate + "\n";
    }
}
