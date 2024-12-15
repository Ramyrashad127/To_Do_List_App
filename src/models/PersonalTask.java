package models;
import java.time.LocalDate;
import java.util.List;

public class PersonalTask extends Task {
    private String category;
    private String location;
    public PersonalTask(String title, String description, String dueDate, int priority, String category, String location) {
        super(title, description, dueDate, priority);
        setCategory(category);
        setLocation(location);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty");
        }
        this.location = location;
    }

    @Override
    public String toString() {
        return super.toString() + "Category: " + category + "\n" + "Location: " + location + "\n";
    }
    
}
