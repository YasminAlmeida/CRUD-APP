package com.trainingcode.resources;

import com.trainingcode.entities.Task;
import com.trainingcode.repositories.TaskRepository;
import com.trainingcode.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/tasks")

public class TaskResource {
    @Autowired
    private TaskService service;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        List<Task> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        Task obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping()
    public ResponseEntity<Task> insert(@RequestBody Task obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
    @RequestMapping(value = "/search",
            method = RequestMethod.GET)
    public ResponseEntity<List<Task>>
    findByUserAndStatusAndPriorityAndCategory(@RequestParam("user_id") Optional <Long> userId,
                                              @RequestParam("status_id") Optional <Long> statusId,
                                              @RequestParam("priority_id") Optional <Long> priorityId,
                                              @RequestParam("category_id") Optional <Long> categoryId) {
        System.out.println("userId: " + userId + "statusId: " + statusId + "priorityId: " + priorityId + "categoryId: " + categoryId);
        List<Task> list =  service.findByUserAndStatusAndPriorityAndCategory(userId,
                statusId, priorityId, categoryId);
        return ResponseEntity.ok().body(list);
    }

    public List<Task> getTasks(){
        return new ArrayList<>();
    }
}
