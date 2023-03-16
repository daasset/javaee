package sprint;

import java.util.Date;

public class Task {
    private Long id;
    private String name;
    private String description;
    private Date dueDate;
    private boolean isDone;

    public Task() {
    }

    public Task(Long id, String name, String description, Date dueDate, boolean isDone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
