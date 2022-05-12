package com.example.springtest1;

import com.example.springtest1.JSONGEtter.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringTest1Application {
    private static final Logger log = LoggerFactory.getLogger(SpringTest1Application.class);


    public static void main(String[] args) {
        SpringApplication.run(SpringTest1Application.class, args);
    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            Greeting greeting = restTemplate.getForObject(
                    "http://localhost:8080/greeting", Greeting.class);
            System.out.println(greeting.toString());
        };
    }

}
