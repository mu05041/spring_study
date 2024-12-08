package com.in28minutes.learn_spring_framework.example.c1;

import org.springframework.stereotype.Component;

@Component
public interface DataService {
    int[] retrieveData();
}
