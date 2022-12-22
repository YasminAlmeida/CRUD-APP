package com.trainingcode.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingcode.entities.enums.Priorities;
import com.trainingcode.entities.enums.TaskStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @Column(nullable = false)
    private Date moment;


    @PrePersist
    public void prePersist() {
        moment = new Date();
    }
//    @Enumerated(EnumType.ORDINAL)
    private Integer taskStatus;
    private Integer priorities;

    private String description;

    //It is possible to have many tasks for one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Task() {
    }
    public Task(Long id, Date moment, TaskStatus taskStatus, User client, Priorities priorities, Category category, String description) {
        super();
        this.id = id;
        this.moment = moment;
        setTaskStatus(taskStatus);
        this.client = client;
        setPriorities(priorities);
        this.category = category;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public Priorities getPriorities() throws IllegalAccessException {
        return Priorities.valueOf(priorities);
    }

    public void setPriorities(Priorities priorities) {
        if (priorities != null) this.priorities = priorities.getCodes();
    }

    public TaskStatus getTaskStatus() throws IllegalAccessException {
        return TaskStatus.valueOf(taskStatus);
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        if (taskStatus != null) this.taskStatus = taskStatus.getCode();
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
