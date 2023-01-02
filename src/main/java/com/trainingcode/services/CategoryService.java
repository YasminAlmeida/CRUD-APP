package com.trainingcode.services;

import com.trainingcode.entities.Category;
import com.trainingcode.repositories.CategoryRepository;
import com.trainingcode.services.exceptions.DatabaseException;
import com.trainingcode.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.get();
    }

    public Category insert(Category obj) {
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

    public Category update(Long id, Category obj) {
        try {
            Category entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    public void updateData(Category entity, Category obj) {
        entity.setName(obj.getName());
    }

}
