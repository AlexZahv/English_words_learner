package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"main"})
@ComponentScan
public class WordsLearnerApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WordsLearnerApplication.class, args);
    }
}