package services;

import models.PersonalTask;
import models.WorkTask;
import models.TodoList;
import java.util.UUID;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.List;

public class TaskService {

    public void createPersonalTask(TodoList todoList, String title, String description, String dueDate, int priority, String category, String location) {
        PersonalTask newTask = new PersonalTask(title, description, dueDate, priority, category, location);
        todoList.addPersonalTask(newTask);
        System.out.println("Personal Task added to the TodoList: " + todoList.getTitle());
    }

    public void createWorkTask(TodoList todoList, String title, String description, String dueDate, int priority, String project, List<String> collaborators, String client) {
        WorkTask newTask = new WorkTask(title, description, dueDate, priority, project, collaborators, client);
        todoList.addWorkTask(newTask);
        System.out.println("Work Task added to the TodoList: " + title);
    }

    public boolean updatePersonalTask(TodoList todoList, UUID taskId, String title, String description, String dueDate, int priority, String category, String location) {
        PersonalTask task = todoList.getPersonalTask(taskId);
        if (task != null) {
            task.setTitle(title);
            task.setDescription(description);
            task.setDueDate(dueDate);
            task.setPriority(priority);
            task.setCategory(category);
            task.setLocation(location);
            System.out.println("Personal Task updated: " + task.getTitle());
            return true;
        }
        System.out.println("Task not found!");
        return false;
    }

    public boolean updateWorkTask(TodoList todoList, UUID taskId, String title, String description, String dueDate, int priority, String project, List<String> collaborators, String client) {
        WorkTask task = todoList.getWorkTask(taskId);
        if (task != null) {
            task.setTitle(title);
            task.setDescription(description);
            task.setDueDate(dueDate);
            task.setPriority(priority);
            task.setProject(project);
            task.setCollaborators(collaborators);
            task.setClient(client);
            System.out.println("Work Task updated: " + task.getTitle());
            return true;
        }
        System.out.println("Task not found!");
        return false;
    }

    public boolean removeTask(TodoList todoList, UUID taskId) {
        if (todoList.removeTask(taskId)) {
            System.out.println("Task removed from the TodoList");
            return true;
        }
        System.out.println("Task not found!");
        return false;
    }

    public String getTaskType(TodoList todoList, UUID taskId) {
        PersonalTask personalTask = todoList.getPersonalTask(taskId);
        if (personalTask != null) {
            return "Personal Task";
        }
        WorkTask workTask = todoList.getWorkTask(taskId);
        if (workTask != null) {
            return "Work Task";
        }
        return null;
    }

    public boolean markTaskComplete(TodoList todoList, UUID taskId) {
        if (todoList == null) {
            throw new IllegalArgumentException("TodoList cannot be null");
        }
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }
        PersonalTask personalTask = todoList.getPersonalTask(taskId);
        if (personalTask != null) {
            personalTask.markComplete();
            System.out.println("Personal Task marked as complete: " + personalTask.getTitle());
            return true;
        }
        WorkTask workTask = todoList.getWorkTask(taskId);
        if (workTask != null) {
            workTask.markComplete();
            System.out.println("Work Task marked as complete: " + workTask.getTitle());
            return true;
        }
        System.out.println("Task not found!");
        return false;
    }

    public boolean markTaskIncomplete(TodoList todoList, UUID taskId) {
        if (todoList == null) {
            throw new IllegalArgumentException("TodoList cannot be null");
        }
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }
        PersonalTask personalTask = todoList.getPersonalTask(taskId);
        if (personalTask != null) {
            personalTask.markIncomplete();
            System.out.println("Personal Task marked as incomplete: " + personalTask.getTitle());
            return true;
        }
        WorkTask workTask = todoList.getWorkTask(taskId);
        if (workTask != null) {
            workTask.markIncomplete();
            System.out.println("Work Task marked as incomplete: " + workTask.getTitle());
            return true;
        }
        System.out.println("Task not found!");
        return false;
    }

    public void showTaskDetails(TodoList todoList, UUID taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }
        if (todoList == null) {
            throw new IllegalArgumentException("TodoList cannot be null");
        }
        PersonalTask personalTask = todoList.getPersonalTask(taskId);
        if (personalTask != null) {
            System.out.println(personalTask);
            return;
        }
        WorkTask workTask = todoList.getWorkTask(taskId);
        if (workTask != null) {
            System.out.println(workTask);
            return;
        }
        System.out.println("Task not found!");
    }

    public void viewAllTasks(TodoList todoList) {
        todoList.listTasks();
    }

    public void viewTasksByPriority(TodoList todoList, int priority) {
        todoList.listTasks(priority);
    }

    public void viewTasksByStatus(TodoList todoList, boolean isComplete) {
        todoList.listTasks(isComplete);
    }

    public void viewTasksByDueDate(TodoList todoList, LocalDate dueDate) {
        todoList.listTasks(dueDate);
    }
}
