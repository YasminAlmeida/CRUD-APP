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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public Page<Task> findByUserAndStatusAndPriorityAndCategory(Optional<Long>  userId, Optional<Long>  statusId,
                                                            Optional<Long>  priorityId, Optional<Long> categoryId,
                                                            int offset, int pageSize) {
        Specification<Task> spec = Specification.where(null);
        spec = spec.and(TaskSpecifications.getByUserAndStatusAndPriorityAndCategory(userId,statusId, priorityId, categoryId));
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.by("id").descending());
        Page<Task> page = repository.findAll(spec, pageable);
        return page;
    }

}
