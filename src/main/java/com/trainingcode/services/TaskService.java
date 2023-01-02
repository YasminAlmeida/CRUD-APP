package com.trainingcode.services;

import com.trainingcode.entities.Task;
import com.trainingcode.repositories.TaskRepository;
import com.trainingcode.repositories.UserRepository;
import com.trainingcode.services.exceptions.DatabaseException;
import com.trainingcode.services.exceptions.ResourceNotFoundException;
import com.trainingcode.specification.TaskSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;
    @Autowired
    private UserRepository userRepository;

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task findById(Long id) {
        Optional<Task> obj = repository.findById(id);
        return obj.get();
    }

    public Task insert(Task obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Task update(Long id, Task obj) {
        try {
            Task entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EmptyResultDataAccessException | IllegalAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Task entity, Task obj) throws IllegalAccessException {
        entity.setDescription(obj.getDescription());
        entity.setUser(obj.getUser());
        entity.setPriorities(obj.getPriorities());
        entity.setTaskStatus(obj.getTaskStatus());
        entity.setCategory(obj.getCategory());
    }

    public List<Task> findByUserAndStatusAndPriorityAndCategory(Optional<Long>  userId, Optional<Long>  statusId, Optional<Long>  priorityId, Optional<Long> categoryId) {
        Specification<Task> spec = Specification.where(null);
        spec = spec.and(TaskSpecifications.getByUserAndStatusAndPriorityAndCategory(userId,statusId, priorityId, categoryId));
        return repository.findAll(spec);
    }


/////////////////////////////
    //In case choice make your own way with sql query
//    public List<Task> findByStatus(Long id) {
//        return repository.findByStatus(id);
//    }
///////////////////////////////////////
}
