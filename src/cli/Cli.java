package cli;
import services.TaskService;
import services.TodoListService;
import java.util.Scanner;
import java.util.UUID;
import java.time.LocalDate;

public class Cli {

    private TodoListService TodoListService;
    private TaskService TaskService;
    private Scanner scanner;
    private String prompt;
    private UUID currentTodoListId;

    public Cli() {
        this.TodoListService = new TodoListService();
        this.TaskService = new TaskService();
        this.scanner = new Scanner(System.in);
        prompt = "> ";
    }

    public void run() {
        System.out.println("Welcome to the TodoList App!");
        System.out.println("Type 'help' to see the available commands.");
        while (true) {
            System.out.print(prompt);
            String command = scanner.nextLine();
            command = command.trim();
            switch (command) {
                case "exit":
                    exit();
                    break;
                case "help":
                    help();
                    break;
                case "create-todo-list":
                    createTodoList();
                    break;
                case "remove-todo-list":
                    removeTodoList();
                    break;
                case "update-todo-list":
                    updateTodoList();
                    break;
                case "list-todo-lists":
                    listTodoLists();
                    break;
                case "select-todo-list":
                    selectTodoList();
                    break;
                case "create-task":
                    createTask();
                    break;
                case "show-currentTodoListId":
                    showCurrentTodoListId();
                    break;
                case "remove-task":
                    removeTask();
                    break;
                case "update-task":
                    updateTask();
                    break;
                case "list-tasks":
                    listTasks();
                    break;
                case "list-tasks-by-priority":
                    listTasksByPriority();
                    break;
                case "list-tasks-by-status":
                    listTasksByStatus();
                    break;
                case "list-tasks-by-due-date":
                    listTasksByDueDate();
                    break;
                default:
                    System.out.println("Invalid command. Type 'help' to see the available commands.");
                    break;
            }
        }
    }

    public void help() {
        System.out.println("create-todo-list");
        System.out.println("remove-todo-list");
        System.out.println("update-todo-list");
        System.out.println("list-todo-lists");
        System.out.println("create-task");
        System.out.println("remove-task");
        System.out.println("update-task");
        System.out.println("mark-task-complete");
        System.out.println("mark-task-incomplete");
        System.out.println("list-tasks");
        System.out.println("list-tasks-by-priority");
        System.out.println("list-tasks-by-status");
        System.out.println("list-tasks-by-due-date");
        System.out.println("help");
        System.out.println("exit");
        System.out.println("select-todo-list");
        System.out.println("show-currentTodoListId"); 
        System.out.println("Note: <todoListId> and <taskId> are UUIDs. Use 'list-todo-lists' and 'list-tasks <todoListId>' to get the UUIDs.");
    }

    public void exit() {
        System.out.println("Exiting TodoList App. Goodbye! ");
        System.exit(0);
    }

    private String getValidInput(String inputMessage, String errorMessage) {
        while (true) {
          System.out.print(inputMessage);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println(errorMessage);
        } else {
            return input;
        }   
        }
    }

    public void createTodoList() {
        String title = getValidInput("Enter the title of the TodoList: ", "Title cannot be empty. Please enter a valid title:");
        String description = getValidInput("Enter the description of the TodoList: ", "Description cannot be empty. Please enter a valid description:");
        
        TodoListService.createTodoList(title, description);
    }

    public void removeTodoList() {
        System.out.print("Enter the UUID of the TodoList you want to remove: ");
        String id = scanner.nextLine().trim();
        try {
            UUID todoListId = UUID.fromString(id);
            if (todoListId.equals(currentTodoListId)) {
                System.out.println("Cannot remove the selected TodoList. Please select another TodoList to remove.");
                return;
            }
            TodoListService.removeTodoList(todoListId);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID. Please enter a valid UUID.");
        }
            
    }

    public void updateTodoList() {
        System.out.print("Enter the UUID of the TodoList you want to update: ");
        String id = scanner.nextLine().trim();
        try {
            UUID todoListId = UUID.fromString(id);
            if(TodoListService.getTodoListById(todoListId) == null) {
                System.out.println("TodoList not found!");
                return;
            }
            String newTitle = getValidInput("Enter the new title of the TodoList: ", "Title cannot be empty. Please enter a valid title:");
            String newDescription = getValidInput("Enter the new description of the TodoList: ", "Description cannot be empty. Please enter a valid description:");
            TodoListService.updateTodoList(todoListId, newTitle, newDescription);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID.");
        }
    }

    public void listTodoLists() {
        TodoListService.listTodoLists();
    }

    public void selectTodoList() {
        System.out.print("Enter the UUID of the TodoList you want to select: ");
        String id = scanner.nextLine().trim();
        try {
            UUID todoListId = UUID.fromString(id);
            if(TodoListService.getTodoListById(todoListId) == null) {
                System.out.println("TodoList not found!");
                return;
            }
            currentTodoListId = todoListId;
            prompt = TodoListService.getTodoListById(todoListId).getTitle() + "> ";
            System.out.println("Selected TodoList: " + TodoListService.getTodoListById(todoListId).getTitle());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID. Please enter a valid UUID.");
        }
    }

    public void showCurrentTodoListId() {
        if (currentTodoListId == null) {
            System.out.println("No TodoList selected. Please select a TodoList using 'select-todo-list <todoListId>' command.");
            return;
        }
        System.out.println(currentTodoListId);
    }

    boolean validdueDate(String dueDate) {
        try {
            LocalDate.parse(dueDate);
            if (LocalDate.parse(dueDate).isBefore(LocalDate.now())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    boolean valid_priority(String priority) {
        try {
            int taskPriority = Integer.parseInt(priority);
            if (taskPriority < 1 || taskPriority > 5) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public void createTask() {
        if (currentTodoListId == null) {
            System.out.println("No TodoList selected. Please select a TodoList using 'select-todo-list <todoListId>' command.");
            return;
        }
        String title = getValidInput("Enter the title of the Task: ", "Title cannot be empty. Please enter a valid title:");
        String description = getValidInput("Enter the description of the Task: ", "Description cannot be empty. Please enter a valid description:");
        String dueDate = getValidInput("Enter the due date of the Task (YYYY-MM-DD): ", "Due date cannot be empty. Please enter a valid due date:");
        if (!validdueDate(dueDate)) {
            System.out.println("Invalid due date.");
            return;
        }
        System.out.print("Enter the priority of the Task (1-5): ");
        String priority = scanner.nextLine().trim();
        if (!valid_priority(priority)) {
            System.out.println("Please enter a valid priority.");
        } else {
            TaskService.addTask(TodoListService.getTodoListById(currentTodoListId), title, description, LocalDate.parse(dueDate), Integer.parseInt(priority));
        }
    }

    public void removeTask() {
        if (currentTodoListId == null) {
            System.out.println("No TodoList selected. Please select a TodoList using 'select-todo-list <todoListId>' command.");
            return;
        }
        System.out.print("Enter the UUID of the Task you want to remove: ");
        String id = scanner.nextLine().trim();
        try {
            UUID taskId = UUID.fromString(id);
            TaskService.removeTask(TodoListService.getTodoListById(currentTodoListId), taskId);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID.");
        }
    }

    public void updateTask() {
        if (currentTodoListId == null) {
            System.out.println("No TodoList selected. Please select a TodoList using 'select-todo-list <todoListId>' command.");
            return;
        }
        System.out.print("Enter the UUID of the Task you want to update: ");
        String id = scanner.nextLine().trim();
        try {
            UUID taskId = UUID.fromString(id);
            if(TodoListService.getTodoListById(currentTodoListId).getTask(taskId) == null) {
                System.out.println("Task not found!");
                return;
            }
            String newTitle = getValidInput("Enter the new title of the Task: ", "Title cannot be empty. Please enter a valid title:");
            String newDescription = getValidInput("Enter the new description of the Task: ", "Description cannot be empty. Please enter a valid description:");
            String newDueDate = getValidInput("Enter the new due date of the Task (YYYY-MM-DD): ", "Due date cannot be empty. Please enter a valid due date:");
            if (!validdueDate(newDueDate)) {
                System.out.println("Invalid due date.");
                return;
            }
            System.out.print("Enter the new priority of the Task (1-5): ");
            String priority = scanner.nextLine().trim();
            if (!valid_priority(priority)) {
                System.out.println("invalid priority.");
            } else {
                TaskService.updateTask(TodoListService.getTodoListById(currentTodoListId), taskId, newTitle, newDescription, LocalDate.parse(newDueDate), Integer.parseInt(priority));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID.");
        }
    }

    public void markTaskComplete() {
        if (currentTodoListId == null) {
            System.out.println("No TodoList selected. Please select a TodoList using 'select-todo-list <todoListId>' command.");
            return;
        }
        System.out.print("Enter the UUID of the Task you want to mark as complete: ");
        String id = scanner.nextLine().trim();
        try {
            UUID taskId = UUID.fromString(id);
            TaskService.markTaskComplete(TodoListService.getTodoListById(currentTodoListId), taskId);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID");
        }
    }
    public void listTasks() {
        if (currentTodoListId == null) {
            System.out.println("No TodoList selected. Please select a TodoList using 'select-todo-list <todoListId>' command.");
            return;
        }
        TaskService.listAllTasks(TodoListService.getTodoListById(currentTodoListId));
    }

    public void listTasksByPriority() {
        if (currentTodoListId == null) {
            System.out.println("No TodoList selected. Please select a TodoList using 'select-todo-list <todoListId>' command.");
            return;
        }
        System.out.print("Enter the priority of the tasks you want to list: ");
        String priority = scanner.nextLine().trim();
        if (!valid_priority(priority)) {
            System.out.println("invalid priority.");
        } else {
            TaskService.listTasksByPriority(TodoListService.getTodoListById(currentTodoListId), Integer.parseInt(priority));
        }   
    }

    public void listTasksByStatus() {
        if (currentTodoListId == null) {
            System.out.println("No TodoList selected. Please select a TodoList using 'select-todo-list <todoListId>' command.");
            return;
        }
        System.out.print("Enter the status of the tasks you want to list (complete/incomplete): ");
        String status = scanner.nextLine().trim();
        if (status.equals("complete")) {
            TaskService.listTasksByStatus(TodoListService.getTodoListById(currentTodoListId), true);
        } else if (status.equals("incomplete")) {
            TaskService.listTasksByStatus(TodoListService.getTodoListById(currentTodoListId), false);
        } else {
            System.out.println("Invalid status.");
        }
    }

    public void listTasksByDueDate() {
        if (currentTodoListId == null) {
            System.out.println("No TodoList selected. Please select a TodoList using 'select-todo-list <todoListId>' command.");
            return;
        }
        System.out.print("Enter the due date of the tasks you want to list (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine().trim();
        if (!validdueDate(dueDate)) {
            System.out.println("Invalid due date.");
        } else {
            TaskService.listTasksByDueDate(TodoListService.getTodoListById(currentTodoListId), LocalDate.parse(dueDate));
        }
    }

}