package com.trainingcode.repositories;

import java.util.List;

import com.trainingcode.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository extends JpaRepository<Task, Long> {


    @Transactional(readOnly = true)
    @Query("SELECT r FROM Task r WHERE r.category.id = :id")
    List<Task> findByCategory(Long id);

    @Transactional(readOnly = true)
    @Query("SELECT r FROM Task r WHERE r.taskStatus = :id")
    List<Task> findByStatus(Long id);

    @Transactional(readOnly = true)
    @Query("SELECT r FROM Task r WHERE r.priorities = :id")
    List<Task> findByPriority(Long id);

    @Transactional(readOnly = true)
    @Query("SELECT r FROM Task r WHERE r.client.id = :id")
    List<Task> findByUser(Long id);

}
