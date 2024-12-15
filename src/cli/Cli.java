package cli;
import services.TaskService;
import services.TodoListService;
import java.util.Scanner;
import java.util.UUID;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import userinput.UserInput;
import models.TodoList;

public class Cli {

    private TodoListService TodoListService;
    private TaskService TaskService;
    private Scanner scanner;
    private String prompt;
    private UUID currentTodoListId;
    private TodoList defaultTodoList;
    private UserInput input;

    public Cli() {
        this.TodoListService = new TodoListService();
        this.TaskService = new TaskService();
        this.scanner = new Scanner(System.in);
        this.input = new UserInput();
        this.defaultTodoList = TodoListService.createTodoList("My Todo", "Default TodoList");
        this.currentTodoListId = defaultTodoList.getId();
        prompt = defaultTodoList.getTitle() + "> ";
    }

    public void run() {
        System.out.println("Welcome to the TodoList App!");
        System.out.println("Type 'help' to see the available commands.");
        while (true) {
            System.out.print(prompt);
            String command = scanner.nextLine().trim();
            command = command.replaceAll(" ", "");
            if (command.equals("")) {
                continue;
            }
            switch (command) {
                case "help":
                    help();
                    break;
                case "exit":
                    exit();
                    break;
                case "createtodolist":
                    createTodoList();
                    break;
                case "updatetodolist":
                    updateTodoList();
                    break;
                case "deletetodolist":
                    deleteTodoList();
                    break;
                case "selecttodolist":
                    selectTodoList();
                    break;
                case "viewtodolists":
                    viewTodoLists();
                    break;
                case "createpersonaltask":
                    createPersonalTask();
                    break;
                case "createworktask":
                    createWorkTask();
                    break;  
                case "updatetask":
                    updateTask();
                    break;
                case "deletetask":
                    deleteTask();
                    break;
                case "viewtasks":
                    viewTasks();
                    break;
                case "completetask":
                    completeTask();
                    break;
                case "viewcompletedtasks":
                    viewCompletedTasks();
                    break;
                case "viewincompletetasks":
                    viewIncompleteTasks();
                    break;
                case "viewtasksbypriority":
                    viewTasksByPriority();
                    break;
                case "viewtasksbyduedate":
                    viewTasksByDueDate();
                    break;
                case "showtaskdetails":
                    showTaskDetails();
                    break;
                case "save":
                    save();
                    break;
                default:
                    System.out.println("Invalid command. Type 'help' to see the available commands.");
                    break;
            }
        }
    }

    public void help() {
        System.out.println("Available commands:");
        System.out.println("1. create todo list");
        System.out.println("2. update todo list");
        System.out.println("3. delete todo list");
        System.out.println("4. select todo list");
        System.out.println("5. view todo lists");
        System.out.println("6. create personal task");
        System.out.println("7. create work task");
        System.out.println("8. update task");
        System.out.println("9. delete task");
        System.out.println("10. view tasks");
        System.out.println("11. complete task");
        System.out.println("12. view completed tasks");
        System.out.println("13. view incomplete tasks");
        System.out.println("14. view tasks by priority");
        System.out.println("15. view tasks by due date");
        System.out.println("16. Show task details");
        System.out.println("17. save");
        System.out.println("18. help");
        System.out.println("19. exit");
    }
    public void exit() {
        TodoListService.save();
        System.out.println("Exiting TodoList App. Goodbye! ");
        System.exit(0);
    }

    public void createTodoList() {
        String title = input.TakeString("Enter the name of the TodoList: ");
        String description = input.TakeString("Enter the description of the TodoList: ");
        if (title == null || description == null) {
            return;
        }
        TodoListService.createTodoList(title, description);
    }

    public void updateTodoList() {
        UUID todoListId = input.TakeId("Enter the ID of the TodoList: ");
        String newTitle = input.TakeString("Enter the new name of the TodoList: ");
        String newDescription = input.TakeString("Enter the new description of the TodoList: ");
        if (newTitle == null || newDescription == null) {
            return;
        }
        TodoListService.updateTodoList(todoListId, newTitle, newDescription);
        if (todoListId.equals(currentTodoListId)) {
            prompt = newTitle + "> ";
        }
    }

    public void deleteTodoList() {
        UUID todoListId = input.TakeId("Enter the ID of the TodoList: ");
        if (todoListId == null) {
            return;
        }
        if (todoListId.equals(defaultTodoList.getId())) {
            System.out.println("Cannot delete the default TodoList.");
            return;
        }
        if (todoListId.equals(currentTodoListId)) {
            currentTodoListId = defaultTodoList.getId();
            prompt = defaultTodoList.getTitle() + "> ";
        }
        TodoListService.removeTodoList(todoListId);
    }

    public void selectTodoList() {
        UUID todoListId = input.TakeId("Enter the ID of the TodoList: ");
        if (todoListId == null) {
            return;
        }
        if (TodoListService.getTodoListById(todoListId) != null) {
            currentTodoListId = todoListId;
            System.out.println("TodoList selected.");
            prompt = TodoListService.getTodoListById(currentTodoListId).getTitle() + "> ";
        } else {
            System.out.println("TodoList not found!");
        }
    }

    public void viewTodoLists() {
        TodoListService.listTodoLists();
    }

    public void createPersonalTask() {
        String title = input.TakeString("Enter the name of the task: ");
        String description = input.TakeString("Enter the description of the task: ");
        String priority = input.TakePriority("Enter the priority of the task (1-5): ");
        String dueDate = input.TakeDate("Enter the due date of the task (yyyy-mm-dd): ");
        String category = input.TakeString("Enter the category of the task: ");
        String location = input.TakeString("Enter the location of the task: ");
        if (title == null || description == null || priority == null || dueDate == null || category == null || location == null) {
            return;
        }
        TaskService.createPersonalTask(TodoListService.getTodoListById(currentTodoListId), title, description, dueDate, Integer.parseInt(priority), category, location);
    }

    public void createWorkTask() {
        String title = input.TakeString("Enter the name of the task: ");
        String description = input.TakeString("Enter the description of the task: ");
        String priority = input.TakePriority("Enter the priority of the task (1-5): ");
        String dueDate = input.TakeDate("Enter the due date of the task (yyyy-mm-dd): ");
        String project = input.TakeString("Enter the project of the task: ");
        if (title == null || description == null || priority == null || dueDate == null || project == null) {
            return;
        }
        List<String> collaborators = new ArrayList<>();
        while (true) {
            String collaborator = input.TakeString("Enter the name of a collaborator (or type 'done' to finish): ");
            if (collaborator == null) {
                return;
            }
            if (collaborator.equals("done")) {
                break;
            }
            collaborators.add(collaborator);
        }
        String client = input.TakeString("Enter the client of the task: ");
        if (client == null) {
            return;
        }
        TaskService.createWorkTask(TodoListService.getTodoListById(currentTodoListId), title, description, dueDate, Integer.parseInt(priority), project, collaborators, client);
    }

    public void updateTask() {
        UUID taskId = input.TakeId("Enter the ID of the task: ");
        if (TaskService.getTaskType(TodoListService.getTodoListById(currentTodoListId), taskId).equals("PersonalTask")) {
            String title = input.TakeString("Enter the new name of the task: ");
            String description = input.TakeString("Enter the new description of the task: ");
            String priority = input.TakePriority("Enter the new priority of the task (1-5): ");
            String dueDate = input.TakeDate("Enter the new due date of the task (yyyy-mm-dd): ");
            String category = input.TakeString("Enter the new category of the task: ");
            String location = input.TakeString("Enter the new location of the task: ");
            if (title == null || description == null || priority == null || dueDate == null || category == null || location == null) {
                return;
            }
            TaskService.updatePersonalTask(TodoListService.getTodoListById(currentTodoListId), taskId, title, description, dueDate, Integer.parseInt(priority), category, location);
        } else {
            String title = input.TakeString("Enter the new name of the task: ");
            String description = input.TakeString("Enter the new description of the task: ");
            String priority = input.TakePriority("Enter the new priority of the task (1-5): ");
            String dueDate = input.TakeDate("Enter the new due date of the task (yyyy-mm-dd): ");
            String project = input.TakeString("Enter the new project of the task: ");
            if (title == null || description == null || priority == null || dueDate == null || project == null) {
                return;
            }
            List<String> collaborators = new ArrayList<>();
            while (true) {
                String collaborator = input.TakeString("Enter the name of a collaborator (or type 'done' to finish): ");
                if (collaborator == null) {
                    return;
                }
                if (collaborator.equals("done")) {
                    break;
                }
                collaborators.add(collaborator);
            }
            String client = input.TakeString("Enter the new client of the task: ");
            if (client == null) {
                return;
            }
            TaskService.updateWorkTask(TodoListService.getTodoListById(currentTodoListId), taskId, title, description, dueDate, Integer.parseInt(priority), project, collaborators, client);
        }
    }

    public void deleteTask() {
        UUID taskId = input.TakeId("Enter the ID of the task: ");
        if (taskId == null) {
            return;
        }
        TaskService.removeTask(TodoListService.getTodoListById(currentTodoListId), taskId);
    }

    public void viewTasks() {
        TaskService.viewAllTasks(TodoListService.getTodoListById(currentTodoListId));
    }

    public void viewCompletedTasks() {
        TaskService.viewTasksByStatus(TodoListService.getTodoListById(currentTodoListId), true);
    }

    public void viewIncompleteTasks() {
        TaskService.viewTasksByStatus(TodoListService.getTodoListById(currentTodoListId), false);
    }

    public void completeTask() {
        UUID taskId = input.TakeId("Enter the ID of the task: ");
        if (taskId == null) {
            return;
        }
        TaskService.markTaskIncomplete(TodoListService.getTodoListById(currentTodoListId), taskId);
    }

    public void viewTasksByPriority() {
        String priority = input.TakePriority("Enter the priority of the tasks (1-5): ");
        if (priority == null) {
            return;
        }
        TaskService.viewTasksByPriority(TodoListService.getTodoListById(currentTodoListId), Integer.parseInt(priority));
    }

    public void viewTasksByDueDate() {
        String dueDate = input.TakeDate("Enter the due date of the tasks (yyyy-mm-dd): ");
        if (dueDate == null) {
            return;
        }
        TaskService.viewTasksByDueDate(TodoListService.getTodoListById(currentTodoListId), LocalDate.parse(dueDate));
    }

    public void showTaskDetails() {
        UUID taskId = input.TakeId("Enter the ID of the task: ");
        if (taskId == null) {
            return;
        }
        TaskService.showTaskDetails(TodoListService.getTodoListById(currentTodoListId), taskId);
    }

    public void save() {
        TodoListService.save();
    }
}