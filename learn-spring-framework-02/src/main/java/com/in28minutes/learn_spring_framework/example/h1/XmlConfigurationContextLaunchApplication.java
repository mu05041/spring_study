package com.in28minutes.learn_spring_framework.example.h1;


import com.in28minutes.learn_spring_framework.game.GameRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;


//xml방식으로 Spring Bean을 생성하는 방법
public class XmlConfigurationContextLaunchApplication {


    public static void main(String[] args) {

        try (var context =
                     new ClassPathXmlApplicationContext("contextConfiguration.xml")) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

            System.out.println(context.getBean("name"));
            System.out.println(context.getBean("age"));
            context.getBean(GameRunner.class).run();

        }


    }


}

