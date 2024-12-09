package com.in28minutes.learn_spring_framework.example.f1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
class SomeClass {
    private final SomeDependency someDependency;
    private SomeDependency dependency;

    public SomeClass(SomeDependency getDependency, SomeDependency someDependency) {
        super();
        this.dependency = getDependency;
        System.out.println("all dependencies are ready");
        this.someDependency = someDependency;
    }
    @PostConstruct
    public void initializer() {
        someDependency.getReady();
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Clean up");
    }
}

@Component
class SomeDependency {
    public void getReady() {
        System.out.println("some logic usign SomeDependency");
    }
}
@Configuration
@ComponentScan
public class PrePostAnnotationContextLaucherApllication {


    public static void main(String[] args) {

        try (var context =
                     new AnnotationConfigApplicationContext(PrePostAnnotationContextLaucherApllication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        }


    }


}

