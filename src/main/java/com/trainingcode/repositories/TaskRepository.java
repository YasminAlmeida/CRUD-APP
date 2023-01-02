package com.trainingcode.repositories;

import com.trainingcode.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskRepository extends JpaRepository<Task, Long>,
        JpaSpecificationExecutor<Task>{


    //In case choice make your own way with sql query
//    @Transactional(readOnly = true)
//    @Query("SELECT r FROM Task r WHERE r.category.id = :id")
//    List<Task> findByCategory(Long id);
//
}
