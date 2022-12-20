package com.trainingcode.services;

import com.trainingcode.entities.Task;
import com.trainingcode.entities.User;
import com.trainingcode.repositories.TaskRepository;
import com.trainingcode.repositories.UserRepository;
import com.trainingcode.services.exceptions.DatabaseException;
import com.trainingcode.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Task entity, Task obj) {
        entity.setDescription(obj.getDescription());
        entity.setMoment(obj.getMoment());
        entity.setClient(obj.getClient());
    }
}
