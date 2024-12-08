package com.in28minutes.learn_spring_framework.example.a0;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
@ComponentScan
public class DepinjectionLaunchApplication {


    public static void main(String[] args) {

        try (var context =
                     new AnnotationConfigApplicationContext(DepinjectionLaunchApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        }


    }


}

