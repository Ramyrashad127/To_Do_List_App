package models;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.LocalDate;
/**
 * The TodoList class represents a to-do list that contains both personal and work tasks.
 * It provides functionality to manage tasks, including adding, removing, listing, and retrieving tasks.
 */
public class TodoList {
    private UUID id;
    private String title;
    private String description;
    private String creationDate;
    private List<PersonalTask> personalTasks;
    private List<WorkTask> workTasks;
 
    /*
     * Constructs a new TodoList with a title and description.
     * The creation date is set to the current date, and the personal and work task lists are initialized as empty.
     * 
     * @param title :The title of the to-do list. Must not be null or empty.
     * @param description :The description of the to-do list. Must not be null or empty.
     * @throws IllegalArgumentException if title or description is invalid.
     */
    public TodoList(String title, String description) {
        this.id = UUID.randomUUID();
        this.creationDate = LocalDate.now().toString();
        this.personalTasks = new ArrayList<>();
        this.workTasks = new ArrayList<>();
        setTitle(title);
        setDescription(description);
    }
 /*
     * @return The unique identifier of the TodoList.
     */
    public UUID getId() {
        return id;
    }

  /**
     * @return The title of the TodoList.
     */

    public String getTitle() {
        return title;
    }
    /*
     * Updates the title of the TodoList.
     * 
     * @param title The new title. Must not be null or empty.
     * @throws IllegalArgumentException if title is invalid.
     */
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
    }
     /*
     * @return The creation date of the TodoList.
     */
    public String getCreationDate() {
        return creationDate;
    }
    /*
     * @return The description of the TodoList.
     */
    public String getDescription() {
        return description;
    }
   /*
     * Updates the description of the TodoList.
     * 
     * @param description The new description. Must not be null or empty.
     * @throws IllegalArgumentException if description is invalid.
     */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description;
    }
  /*
     * Adds a personal task to the TodoList.
     * 
     * @param task The personal task to add. Must not be null.
     * @throws IllegalArgumentException if task is null.
     */
    public void addPersonalTask(PersonalTask task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        personalTasks.add(task);
    }
     /* 

     * Adds a work task to the TodoList.
     * 
     * @param task The work task to add. Must not be null.
     * @throws IllegalArgumentException if task is null.
     */
    public void addWorkTask(WorkTask task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        workTasks.add(task);
    }
  /*
     * Removes a task (either personal or work) by its unique identifier.
     * 
     * @param taskId The unique identifier of the task to remove. Must not be null.
     * @return true if the task was successfully removed, false otherwise.
     * @throws IllegalArgumentException if taskId is null.
     */
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
 /*
     * Retrieves a personal task by its unique identifier.
     * 
     * @param taskId The unique identifier of the task to retrieve. Must not be null.
     * @return The personal task if found, or null otherwise.
     * @throws IllegalArgumentException if taskId is null.
     */
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
 /*
     * Retrieves a work task by its unique identifier.
     * 
     * @param taskId The unique identifier of the task to retrieve. Must not be null.
     * @return The work task if found, or null otherwise.
     * @throws IllegalArgumentException if taskId is null.
     */
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
 /*
     * Lists all tasks in the TodoList, including personal and work tasks.
     * If no tasks exist, a message is displayed.
     */
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
  /*
     * Lists tasks in the TodoList that match a specified priority.
     * 
     * @param priority The priority level to filter tasks by.
     */
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
/*
     * Lists tasks in the TodoList that match the specified completion status.
     * 
     * @param isComplete true to list completed tasks, false to list incomplete tasks.
     */
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
     * Lists tasks in the TodoList that are due on a specific date.
     * 
     * @param dueDate The due date to filter tasks by. Must not be null.
     * @throws IllegalArgumentException if dueDate is null.
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
