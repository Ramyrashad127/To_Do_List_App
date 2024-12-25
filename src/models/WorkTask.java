package models;
import java.time.LocalDate;
import java.util.List;

/*
 * The WorkTask class extends the Task class to represent a work-related task.
 * It includes additional attributes such as the project name, collaborators, and client.
 * 
 * This class provides getters and setters for these attributes, 
 * along with input validation to ensure meaningful values are assigned.
  */
public class WorkTask extends Task {
    private String project;
    private List<String> collaborators;
    private String client;
     /*
     * Constructor for the WorkTask class.
     * 
     * @param title          The title of the task (inherited from Task).
     * @param description    The description of the task (inherited from Task).
     * @param dueDate        The due date of the task in String format (inherited from Task).
     * @param priority       The priority level of the task (inherited from Task).
     * @param project        The name of the project associated with this task.
     * @param collaborators  A list of collaborators involved in this task.
     * @param client         The client for whom this task is being performed.
     * @throws IllegalArgumentException if project, collaborators, or client are null or invalid.
     */
    public WorkTask(String title, String description, String dueDate, int priority, String project, List<String> collaborators, String client) {
        super(title, description, dueDate, priority);
        setProject(project);
        setCollaborators(collaborators);
        setClient(client);
    }

    /*
     * Retrieves the project name associated with this task.
     * 
     * @return The project name.
     */
     
    public String getProject() {
        return project;
    }

    /*
     * Sets the project name for this task.
     * 
     * @param project The name of the project.
     * @throws IllegalArgumentException if project is null or empty.
     */
    public void setProject(String project) {
        if (project == null || project.trim().isEmpty()) {
            throw new IllegalArgumentException("Project cannot be empty");
        }
        this.project = project;
    }

    /*
     * Retrieves the collaborators for this task.
     * 
     * @return A list of collaborators.
     */
    public List<String> getCollaborators() {
        return collaborators;
    }

    /*
     * Sets the list of collaborators for this task.
     * 
     * @param collaborators A list of collaborators.
     * @throws IllegalArgumentException if collaborators is null or empty.
     */
    public void setCollaborators(List<String> collaborators) {
        if (collaborators == null || collaborators.isEmpty()) {
            throw new IllegalArgumentException("Collaborators cannot be empty");
        }
        this.collaborators = collaborators;
    }

    /*
     * Retrieves the client associated with this task.
     * 
     * @return The client's name.
     */

    public String getClient() {
        return client;
    }

    /*
     * Sets the client for this task.
     * 
     * @param client The client's name.
     * @throws IllegalArgumentException if client is null or empty.
     */

    public void setClient(String client) {
        if (client == null || client.trim().isEmpty()) {
            throw new IllegalArgumentException("Client cannot be empty");
        }
        this.client = client;
    }

    /*
     * Returns a string representation of the WorkTask object,
     * including details from the parent Task class.
     * 
     * @return A formatted string with task details.
     */
    @Override
    public String toString() {
        return super.toString() + "Project: " + project + "\n" + "Collaborators: " + collaborators + "\n" + "Client: " + client + "\n";
    }

}
