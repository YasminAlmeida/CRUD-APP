package com.trainingcode.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingcode.entities.enums.TaskStatus;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer taskStatus;

    //It is possible to have many tasks for one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;

    public Task() {

    }

    public Task(Long id, Instant moment, TaskStatus taskStatus, User client) {
        super();
        this.id = id;
        this.moment = moment;
        setTaskStatus(taskStatus);
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public TaskStatus getTaskStatus() throws IllegalAccessException {
        return TaskStatus.valueOf(taskStatus);
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        if(taskStatus != null) this.taskStatus = taskStatus.getCode();
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
