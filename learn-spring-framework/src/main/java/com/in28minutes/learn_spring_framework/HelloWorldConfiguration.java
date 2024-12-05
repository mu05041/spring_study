package com.in28minutes.learn_spring_framework;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 레코드를 사용하면 세터 게터 생성자를 만들 필요 x
// 장고의 Serializer와 비슷한 역할을 한다. API로 보낼때 필요한것만 골라서 보냄
record Person (String name, int age, Address address) {};
record Address (String firstLine, String city) {};

@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String name() {
        return "Miyeon";
    }

    @Bean
    public int age() {
        return 15;
    }
    @Bean
    public Person person() {
        var person = new Person("Mi", 18, new Address("Main Street", "Utrecht"));
        return person;

    }

    @Bean
    public Person person2MethodCall() {
        return new Person(name(), age(), address());

    }
    @Bean
    public Person person3MethodCall(String name, int age, Address address3) {
        return new Person(name, age, address3);

    }
    @Bean(name = "address2")
    public Address address() {
        var address = new Address("sicheong-ro", "wonju");
        return address;
    }

    @Bean(name = "address3")
    public Address address3() {
        var address = new Address("new street", "new york");
        return address;
    }

}
