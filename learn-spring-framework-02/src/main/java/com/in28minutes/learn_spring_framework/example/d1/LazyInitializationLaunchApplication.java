package com.in28minutes.learn_spring_framework.example.d1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class  ClassA {


}
@Component
@Lazy
class  ClassB {
    private ClassA classA;
    public ClassB(ClassA classA) {
        System.out.println("Some initialization logic");
        this.classA = classA;
    }
    public void doSomething() {
        System.out.println("Do Something");
    }
}


@Configuration
@ComponentScan
public class LazyInitializationLaunchApplication {


    public static void main(String[] args) {

        try (var context =
                     new AnnotationConfigApplicationContext(LazyInitializationLaunchApplication.class)) {

            System.out.println("Initialization of context is completed");
            context.getBean(ClassB.class).doSomething();
        }


    }


}

