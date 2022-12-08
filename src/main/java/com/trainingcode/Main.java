package com.trainingcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    @GetMapping("/")
     public GreetResponse greet(){
        GreetResponse response = new GreetResponse(
                "hello",
                List.of("Java", "Golang", "JvaScript"),
                new Person("Alex", 28, 3.555)
                );
        return response;
     }
     record  Person(String name, int age, double saving){
     }
    record GreetResponse(
            String greet,
            List<String> favProgramingLinguages,
            Person person
            ){}

    //class GreetResponse{
    //   private final String greet;

    //     GreetResponse(String greet) {
    //        this.greet = greet;
    //    }

    //     public String getGreet() {
    //         return greet;
    //     }

    //     @Override
    //    public String toString() {
    //        return "GreetResponse{" +
    //                "greet='" + greet + '\'' +
    //                '}';
    //    }

    //}
}
