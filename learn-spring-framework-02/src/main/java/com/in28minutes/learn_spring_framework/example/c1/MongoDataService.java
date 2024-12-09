package com.in28minutes.learn_spring_framework.example.c1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class MongoDataService implements DataService {

    @Override
    public int[] retrieveData(){
        return new int[] {1, 2, 3, 4, 77};

    }
}
