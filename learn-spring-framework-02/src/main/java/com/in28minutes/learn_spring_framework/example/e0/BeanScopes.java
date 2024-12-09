package com.in28minutes.learn_spring_framework.example.e0;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class NormalClass {

}

// 스프링에서 생성되는 모든 Bean은 싱글톤이다. 그러나 Bean생성마다 새거를 가져오고싶으면 PROTOTYPE을 이용하면 된다
@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class PrototypeClass {

}

@Configuration
@ComponentScan
public class BeanScopes {


    public static void main(String[] args) {

        try (var context =
                     new AnnotationConfigApplicationContext(BeanScopes.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            //프로토타입은 호출할때마다 다른 해시코드가 나타나는데 매번 Spring컨텍스트에서 새로운 Bean을 가져오기 떄문이다
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));
        }


    }


}

