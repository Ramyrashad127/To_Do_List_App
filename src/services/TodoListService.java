package services;

import models.TodoList;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import storage.FileStorage;

public class TodoListService {
    private List<TodoList> todoLists = new ArrayList<>();
    private FileStorage fileStorage;

    /*
     * Constructor to initialize the FileStorage object and load the saved TodoLists
     * from the file. If the file does not exist, it will create a new file.
     * If the file is empty, it will initialize an empty list of TodoLists.
     * If the file is not empty, it will load the saved TodoLists from the file.
     * @return void
     */
    public TodoListService() {
        this.fileStorage = new FileStorage("todoLists.json");
        List<TodoList> savedTodoLists = fileStorage.load();
        if (savedTodoLists != null) {
            todoLists = savedTodoLists;
        }
    }

    /*
     * Create a new TodoList with the given title and description.
     * @param title: The title of the TodoList
     * @param description: The description of the TodoList
     * @return TodoList: The newly created TodoList
     * @return void
     */
    public TodoList createTodoList(String title, String description) {
        TodoList newTodoList = new TodoList(title, description);
        todoLists.add(newTodoList);
        System.out.println("New TodoList created: " + title);
        return newTodoList;
    }

    /*
     * Remove a TodoList with the given ID.
     * @param todoListId: The ID of the TodoList to be removed
     * @return boolean: True if the TodoList is removed successfully, False otherwise
     */
    public boolean removeTodoList(UUID todoListId) {
        for (TodoList todoList : todoLists) {
            if (todoList.getId().equals(todoListId)) {
                todoLists.remove(todoList);
                System.out.println("TodoList removed successfully.");
                return true;
            }
        }
        System.out.println("TodoList not found!");
        return false;
    }

    /*
     * Update the title and description of a TodoList with the given ID.
     * @param todoListId: The ID of the TodoList to be updated
     * @param newTitle: The new title of the TodoList
     * @param newDescription: The new description of the TodoList
     * @return boolean: True if the TodoList is updated successfully, False otherwise
     */
    public boolean updateTodoList(UUID todoListId, String newTitle, String newDescription) {
        TodoList todoList = getTodoListById(todoListId);
        if (todoList != null) {
            todoList.setTitle(newTitle);
            todoList.setDescription(newDescription);
            System.out.println("TodoList updated: " + todoList.getTitle());
            return true;
        }
        System.out.println("TodoList not found!");
        return false;
    }

    /*
     * Get a TodoList with the given ID.
     * @param todoListId: The ID of the TodoList to be retrieved
     * @return TodoList: The TodoList with the given ID, or null if not found
     */
    public TodoList getTodoListById(UUID todoListId) {
        for (TodoList todoList : todoLists) {
            if (todoList.getId().equals(todoListId)) {
                return todoList;
            }
        }
        return null;
    }

    /*
     * Get a TodoList with the given ID.
     * @param todoListId: The ID of the TodoList to be retrieved
     * @return TodoList: The TodoList with the given ID, or null if not found
     */
    public TodoList getTodoList(UUID todoListId) {
        return getTodoListById(todoListId);
    }

    /*
     * List all TodoLists available.
     * @return void
     */
    public void listTodoLists() {
        if (todoLists.isEmpty()) {
            System.out.println("No TodoLists available.");
        } else {
            System.out.println("All TodoLists:");
            for (TodoList todoList : todoLists) {
                System.out.println(todoList);
            }
        }
    }

    /*
     * Count the number of TodoLists available.
     * @return int: The number of TodoLists available
     */
    public int countTodoLists() {
        return todoLists.size();
    }

    /*
     * Save the TodoLists to the file.
     * @return void
     */
    public void save(){
        fileStorage.save(todoLists);
    }

    /*
     * Get the default TodoList.
     * If there are no TodoLists available, it will create a new default TodoList.
     * @return TodoList: The default TodoList
     */
    public TodoList getDefauTodoList() {
        if (todoLists.isEmpty()) {
            return createTodoList("My Todo", "Default TodoList");
        }
        return todoLists.get(0);
    }

}
