package com.in28minutes.learn_spring_framework.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {

    public static void main(String[] args) {
        //1. Lauch a Spring Context
        //장고로 비유하자면, 장고는 python manage.py runserver 명령어를 실행할 때
        // 자동으로 Django 애플리케이션 컨텍스트가 시작되는 것과 비슷함.
        try(var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)) {

            //2. Configure the things that we want Spring to manage @Configuration
            // HelloWOrldConfiguration - @ Configuration
            // name -@Bean

            //3. Retrieving Beans managed by Spring
            System.out.println(context.getBean("name"));

            System.out.println(context.getBean("age"));

            System.out.println(context.getBean("person"));

            System.out.println(context.getBean("person2MethodCall"));
            System.out.println(context.getBean("person3MethodCall"));

            System.out.println(context.getBean("address2"));

            System.out.println(context.getBean(Person.class));
            System.out.println(context.getBean(Address.class));

            System.out.println(context.getBean("person5Qualifier"));


//        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        };

    }

}
