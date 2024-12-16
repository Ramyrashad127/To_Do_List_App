# To-Do List Application

This is a simple Java-based To-Do List application that allows you to create, update, delete, and view to-do lists and tasks. You can manage both personal and work-related tasks and view tasks based on priority, due date, or completion status.

## Features

- Create, update, delete, and select to-do lists.
- Manage tasks (personal and work-related) within to-do lists.
- View tasks by completion status, priority, or due date.
- Save tasks and to-do lists.
- Help command for guidance on available commands.

## Available Commands

1. Create todo list  
2. Update todo list  
3. Delete todo list  
4. Select todo list  
5. View todo lists  
6. Create personal task  
7. Create work task  
8. Update task  
9. Delete task  
10. View tasks  
11. Complete task  
12. View completed tasks  
13. View incomplete tasks  
14. View tasks by priority  
15. View tasks by due date  
16. Show task details  
17. Save  
18. Help  
19. Exit  

## Prerequisites

- **Java Development Kit (JDK)** - Make sure JDK is installed on your system. You can download it from [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
- **Git** - If you don't have Git installed, you can download it from [here](https://git-scm.com/downloads).

## How to Clone the Repository

1. Open your terminal or command prompt.
2. Run the following command to clone the repository:

```bash
git clone https://github.com/Ramyrashad127/to_do_list_app.git
```

## How to Compile and Run

1. Navigate to the project directory:
    ```bash
    cd to_do_list_app
    ```
2. Compile the project:
    ```bash
    javac -cp "lib/*" -d out src/Main.java src/cli/*.java src/models/*.java src/services/*.java src/userinput/*.java src/storage/*.java
    ```
3. Run the application:
    ```bash
    java -cp "out;lib/*" Main
    ```