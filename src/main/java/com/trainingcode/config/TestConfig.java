package com.trainingcode.config;

import com.trainingcode.entities.Category;
import com.trainingcode.entities.Priorities;
import com.trainingcode.entities.Task;
import com.trainingcode.entities.User;
import com.trainingcode.entities.enums.TaskStatus;
import com.trainingcode.repositories.CategoryRepository;
import com.trainingcode.repositories.PrioritiesRepository;
import com.trainingcode.repositories.TaskRepository;
import com.trainingcode.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

//database seeding
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    //dependency association
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PrioritiesRepository prioritiesRepository;
    @Override
    public void run(String... args) throws Exception {
        Category cat1 = new Category(null, "Work");
        Category cat2 = new Category(null, "Learning");
        Category cat3 = new Category(null, "Self Care");

        Priorities p1 = new Priorities(null, "Urgent", "");
        Priorities p2 = new Priorities(null, "High", "");
        Priorities p3 = new Priorities(null, "Normal", "");
        Priorities p4 = new Priorities(null, "Low", "");

        categoryRepository.saveAll((Arrays.asList(cat1, cat2, cat3)));
        prioritiesRepository.saveAll((Arrays.asList(p1, p2, p3, p4)));

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Task o1 = new Task(null, Instant.parse("2019-06-20T19:53:07Z"), TaskStatus.TASK_OPEN, u1);
        Task o2 = new Task(null, Instant.parse("2019-07-21T03:42:10Z"), TaskStatus.TASK_INPROGRESS, u2);
        Task o3 = new Task(null, Instant.parse("2019-07-22T15:21:22Z"), TaskStatus.TASK_CLOSED, u1);

        userRepository.saveAll(Arrays.asList(u1, u2));
        taskRepository.saveAll(Arrays.asList(o1, o2, o3));

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat1);

        prioritiesRepository.saveAll((Arrays.asList(p1, p2, p3, p4)));
    }
}
