package com.trainingcode.config;

import com.trainingcode.entities.Category;
import com.trainingcode.entities.Task;
import com.trainingcode.entities.User;
import com.trainingcode.entities.enums.Priorities;
import com.trainingcode.entities.enums.TaskStatus;
import com.trainingcode.repositories.CategoryRepository;
import com.trainingcode.repositories.TaskRepository;
import com.trainingcode.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

//database seeding
@Configuration
public class CorsConfiguration implements WebMvcConfigurer, CommandLineRunner {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public void run(String... args) throws Exception {
        Category cat1 = new Category(null, "Work");
        Category cat2 = new Category(null, "Learning");
        Category cat3 = new Category(null, "Self Care");

        categoryRepository.saveAll((Arrays.asList(cat1, cat2, cat3)));

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Task o1 = new Task(null, new Date(), TaskStatus.Open, u1, Priorities.LOW, cat3,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin quis lectus sit amet eros tincidunt maximus. Curabitur ullamcorper tristique nunc.");
        Task o2 = new Task(null, new Date(), TaskStatus.InProgress, u2, Priorities.HIGH, cat2," Etiam ut mi sit amet est mattis consectetur et sed ante. Mauris ultrices neque ornare tincidunt dapibus. Praesent porta commodo blandit. ");
        Task o3 = new Task(null, new Date(), TaskStatus.Closed, u1, Priorities.URGENT, cat1,"Maecenas blandit pellentesque neque, et placerat mauris rhoncus vel. In nec ultricies lectus. Sed ipsum nisl, maximus sit amet dolor ut, tempor rutrum felis.");
        Task o4 = new Task(null, new Date(), TaskStatus.Open, u1, Priorities.NORMAL, cat1,"Maecenas blandit pellentesque neque, et placerat mauris rhoncus vel. In nec ultricies lectus. Sed ipsum nisl, maximus sit amet dolor ut, tempor rutrum felis.");
        Task o5 = new Task(null, new Date(), TaskStatus.InProgress, u1, Priorities.LOW, cat3,"Aenean volutpat rhoncus justo vitae interdum. Etiam posuere arcu eget mattis iaculis. Pellentesque bibendum tortor tellus, in iaculis eros sodales nec.");
        Task o6 = new Task(null, new Date(), TaskStatus.Closed, u1, Priorities.HIGH, cat2,"Maecenas blandit pellentesque neque, et placerat mauris rhoncus vel. In nec ultricies lectus. Sed ipsum nisl, maximus sit amet dolor ut, tempor rutrum felis.");

        userRepository.saveAll(Arrays.asList(u1, u2));
        taskRepository.saveAll(Arrays.asList(o1, o2, o3, o4, o5, o6));

    }
}
