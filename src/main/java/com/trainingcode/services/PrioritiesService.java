package com.trainingcode.services;

import com.trainingcode.entities.Priorities;
import com.trainingcode.repositories.PrioritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrioritiesService {
    @Autowired
    private PrioritiesRepository repository;

    public List<Priorities> findAll() {
        return repository.findAll();
    }

    public Priorities findById(Long id) {
        Optional<Priorities> obj = repository.findById(id);
        return obj.get();
    }
}
