package com.in28minutes.learn_spring_framework.example.a1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class YourBusinessClass{
    Dependecny1 dependecy1;
    Dependecny2 dependecy2;

//    @Autowired
    public YourBusinessClass(Dependecny1 dependecy1, Dependecny2 dependecy2) {
        super();
        System.out.println("Constructor Injected");
        this.dependecy1 = dependecy1;
        this.dependecy2 = dependecy2;
    }

//    @Autowired
//    public void setDependecy1(Dependecny1 dependecy1) {
//        System.out.println("setterInjection1");
//        this.dependecy1 = dependecy1;
//    }
//
//
//    @Autowired
//    public void setDependecy2(Dependecny2 dependecy2) {
//        System.out.println("setterInjection2");
//
//        this.dependecy2 = dependecy2;
//    }



    public String toString() {
        return "Using" + dependecy1 + " and " + dependecy2;
    }
}

@Component
class Dependecny1{

}

@Component
class Dependecny2{

}

@Configuration
@ComponentScan
public class DepinjectionLaunchApplication {


    public static void main(String[] args) {

        try (var context =
                     new AnnotationConfigApplicationContext(DepinjectionLaunchApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(context.getBean(YourBusinessClass.class));

        }


    }


}

