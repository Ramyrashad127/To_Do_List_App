package services;

import models.Task;
import models.TodoList;
import java.util.UUID;
import java.util.List;
import java.time.LocalDate;

public class TaskService {
    public void addTask(TodoList todoList, String title, String description, LocalDate dueDate, int priority) {
        Task newTask = new Task(title, description, dueDate, priority);
        todoList.addTask(newTask);
        System.out.println("Task added to the TodoList: " + title);
    }

    public boolean removeTask(TodoList todoList, UUID taskId) {
        if (todoList.removeTask(taskId)) {
            System.out.println("Task removed from the TodoList");
            return true;
        }
        System.out.println("Task not found!");
        return false;
    }

    public boolean updateTask(TodoList todoList, UUID taskId, String newTitle, String newDescription, LocalDate newDueDate, int newPriority) {
        Task task = todoList.getTask(taskId);
        if (task != null) {
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            task.setDueDate(newDueDate);
            task.setPriority(newPriority);
            System.out.println("Task updated: " + task.getTitle());
            return true;
        }
        System.out.println("Task not found!");
        return false;
    }

    public boolean markTaskComplete(TodoList todoList, UUID taskId) {
        Task task = todoList.getTask(taskId);
        if (task != null) {
            task.markComplete();
            System.out.println("Task marked as complete: " + task.getTitle());
            return true;
        }
        System.out.println("Task not found!");
        return false;
    }

    public boolean markTaskIncomplete(TodoList todoList, UUID taskId) {
        Task task = todoList.getTask(taskId);
        if (task != null) {
            task.markIncomplete();
            System.out.println("Task marked as incomplete: " + task.getTitle());
            return true;
        }
        System.out.println("Task not found!");
        return false;
    }

    public Task getTaskDetails(TodoList todoList, UUID taskId) {
        return todoList.getTask(taskId);
    }

    public void listAllTasks(TodoList todoList) {
        todoList.listTasks();
    }

    public void listTasksByPriority(TodoList todoList, int priority) {
        todoList.listTasks(priority);
    }

    public void listTasksByStatus(TodoList todoList, boolean isComplete) {
        todoList.listTasks(isComplete);
    }

    public void listTasksByDueDate(TodoList todoList, LocalDate dueDate) {
        todoList.listTasks(dueDate);
    }
}
