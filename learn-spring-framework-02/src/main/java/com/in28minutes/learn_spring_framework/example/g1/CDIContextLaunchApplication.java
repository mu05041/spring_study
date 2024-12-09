package com.in28minutes.learn_spring_framework.example.g1;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Component
@Named
class Businesservice {
    private DataService dataService;

//    @Autowired
    @Inject
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }
    public DataService getDataService() {
        System.out.println("SetterInjection");
        return dataService;
    }


}
@Named
class DataService {

}


@Configuration
@ComponentScan
public class CDIContextLaunchApplication {

    public static void main(String[] args) {

        try (var context =
                     new AnnotationConfigApplicationContext(CDIContextLaunchApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(context.getBean(Businesservice.class).getDataService());

        }


    }


}

