package models;
import java.time.LocalDate;
import java.util.List;

public class WorkTask extends Task {
    private String project;
    private List<String> collaborators;
    private String client;
    public WorkTask(String title, String description, String dueDate, int priority, String project, List<String> collaborators, String client) {
        super(title, description, dueDate, priority);
        setProject(project);
        setCollaborators(collaborators);
        setClient(client);
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        if (project == null || project.trim().isEmpty()) {
            throw new IllegalArgumentException("Project cannot be empty");
        }
        this.project = project;
    }

    public List<String> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<String> collaborators) {
        if (collaborators == null || collaborators.isEmpty()) {
            throw new IllegalArgumentException("Collaborators cannot be empty");
        }
        this.collaborators = collaborators;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        if (client == null || client.trim().isEmpty()) {
            throw new IllegalArgumentException("Client cannot be empty");
        }
        this.client = client;
    }

    @Override
    public String toString() {
        return super.toString() + "Project: " + project + "\n" + "Collaborators: " + collaborators + "\n" + "Client: " + client + "\n";
    }

}
