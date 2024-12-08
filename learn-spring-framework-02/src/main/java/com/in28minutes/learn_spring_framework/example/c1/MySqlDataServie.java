package com.in28minutes.learn_spring_framework.example.c1;

import org.springframework.stereotype.Component;

@Component
public class MySqlDataServie implements DataService {

    @Override
    public int[] retrieveData() {
        return new int[] {11, 22, 33, 44, 55};

    }
}
