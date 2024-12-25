package services;

import models.PersonalTask;
import models.WorkTask;
import models.TodoList;
import java.util.UUID;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.List;

public class TaskService {
 /*
 * Creates a new personal task and adds it to the specified todoList
 * 
 * @param todoList the todoList object where the task will be added
 * @param title the title of the task
 * @param description a brief description of the task
 * @param dueDate the due date for the task in a string format
 * @param priority the priority level of the task (1 to 5)
 * @param category the category of the task
 * @param location the location associated with the task
 * @return void
 */

    public void createPersonalTask(TodoList todoList, String title, String description, String dueDate, int priority, String category, String location) {
        PersonalTask newTask = new PersonalTask(title, description, dueDate, priority, category, location);
        todoList.addPersonalTask(newTask);
        System.out.println("Personal Task added to the TodoList: " + todoList.getTitle());
    }

    /*
     * Creates a new work task and adds it to the specified todoList
     * @param todoList the TodoList object where the task will be added
     * @param title the title of the task
     * @param description a brief description of the task
     * @param dueDate the due date for the task in a string format
     * @param priority the priority level of the task (1 to 5)
     * @param project the project associated with the task
     * @param collaborators the list of collaborators for the task
     * @param client the client associated with the task
     * @return void
     */
    public void createWorkTask(TodoList todoList, String title, String description, String dueDate, int priority, String project, List<String> collaborators, String client) {
        WorkTask newTask = new WorkTask(title, description, dueDate, priority, project, collaborators, client);
        todoList.addWorkTask(newTask);
        System.out.println("Work Task added to the TodoList: " + title);
    }
    /*
     * Updates an existing personal task in the specified todoList
     * @param todoList the TodoList object containing the personal task
     * @param taskId the unique identifier (UUID) of the personal task to be updated
     * @param title the new title for the task
     * @param description the new description for the task
     * @param dueDate the new due date for the task as a string
     * @param priority the new priority level for the task (1 to 5)
     * @param category the new category for the task
     * @param location the new location for the task
     */
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
    /*
     * Updates an existing work task in the specified todoList
     * @param todoList the TodoList object containing the work task
     * @param taskId the unique identifier (UUID) of the work task to be updated
     * @param title the new title for the task
     * @param description the new description for the task
     * @param dueDate the new due date for the task as a string
     * @param priority the new priority level for the task (1 to 5)
     * @param project the new project for the task
     * @param collaborators the new list of collaborators for the task
     * @param client the new client for the task
     */
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

    /*
     * Removes a task from the specified todoList
     * @param todoList the TodoList object containing the task
     * @param taskId the unique identifier (UUID) of the task to be removed
     * @return true if the task was successfully removed, false if the task was not found
     */
    public boolean removeTask(TodoList todoList, UUID taskId) {
        if (todoList.removeTask(taskId)) {
            System.out.println("Task removed from the TodoList");
            return true;
        }
        System.out.println("Task not found!");
        return false;
    }

    /*
     * Retrieves the type of a task from the specified todoList
     * @param todoList the TodoList object containing the tasks
     * @param taskId the unique identifier (UUID) of the task
     * @return the type of the task (Personal Task or Work Task) or null if the task was not found
     */
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

    /*
     * Marks a task as complete in the specified todoList
     * @param todoList the TodoList object containing the tasks
     * @param taskId the unique identifier (UUID) of the task to be marked as complete
     * @return true if the task was successfully marked as complete, false if the task was not found
     */
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

    /*
     * Marks a task as incomplete in the specified todoList
     * @param todoList the TodoList object containing the tasks
     * @param taskId the unique identifier (UUID) of the task to be marked as incomplete
     * @return true if the task was successfully marked as incomplete, false if the task was not found
     */
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

    /*
     * Shows the details of a task in the specified todoList
     * @param todoList the TodoList object containing the tasks
     * @param taskId the unique identifier (UUID) of the task to be displayed
     * @return void
     */
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

    /*
     * Shows all tasks in the specified todoList
     * @param todoList the TodoList object containing the tasks
     * @return void
     */
    public void viewAllTasks(TodoList todoList) {
        todoList.listTasks();
    }

    /*
     * Shows tasks in the specified todoList with the specified priority
     * @param todoList the TodoList object containing the tasks
     * @param priority the priority level of the tasks to be displayed
     * @return void
     */
    public void viewTasksByPriority(TodoList todoList, int priority) {
        todoList.listTasks(priority);
    }

    /*
     * Shows tasks in the specified todoList with the specified status (complete or incomplete)
     * @param todoList the TodoList object containing the tasks
     * @param isComplete true if the tasks to be displayed are complete, false if they are incomplete
     * @return void
     */
    public void viewTasksByStatus(TodoList todoList, boolean isComplete) {
        todoList.listTasks(isComplete);
    }

    /*
     * Shows tasks in the specified todoList with the specified due date
     * @param todoList the TodoList object containing the tasks
     * @param dueDate the due date of the tasks to be displayed
     * @return void
     */
    public void viewTasksByDueDate(TodoList todoList, LocalDate dueDate) {
        todoList.listTasks(dueDate);
    }
}
