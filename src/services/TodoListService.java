package services;

import models.TodoList;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import storage.FileStorage;

public class TodoListService {
    private List<TodoList> todoLists = new ArrayList<>();
    private FileStorage fileStorage;

    public TodoListService() {
        this.fileStorage = new FileStorage("todoLists.json");
        List<TodoList> savedTodoLists = fileStorage.load();
        if (savedTodoLists != null) {
            todoLists = savedTodoLists;
        }
    }

    public TodoList createTodoList(String title, String description) {
        TodoList newTodoList = new TodoList(title, description);
        todoLists.add(newTodoList);
        System.out.println("New TodoList created: " + title);
        return newTodoList;
    }

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

    public TodoList getTodoListById(UUID todoListId) {
        for (TodoList todoList : todoLists) {
            if (todoList.getId().equals(todoListId)) {
                return todoList;
            }
        }
        return null;
    }

    public TodoList getTodoList(UUID todoListId) {
        return getTodoListById(todoListId);
    }

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

    public int countTodoLists() {
        return todoLists.size();
    }

    public void save(){
        fileStorage.save(todoLists);
    }

    public TodoList getDefauTodoList() {
        return todoLists.get(0);
    }

}
