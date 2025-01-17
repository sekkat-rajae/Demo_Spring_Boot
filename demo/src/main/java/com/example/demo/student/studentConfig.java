package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class studentConfig {

    @Bean
    CommandLineRunner commandLineRunner (studentRepository repository){
      return args ->{
          student salma = new student(
                          "salma",
                          "rajae@gmail.com",
                          LocalDate.of(2002, Month.SEPTEMBER, 23)

          );
          student youssef = new student(
                          "youssef",
                          "youssef@gmail.com",
                          LocalDate.of(1996, Month.MARCH, 21)

          );

          repository.saveAll(
                  List.of(salma,youssef)
          );
      };
    }
}
