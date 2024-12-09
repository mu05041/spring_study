package com.in28minutes.learn_spring_framework.example.c1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//import com.in28minutes.learn_spring_framework.example.c1.DataService;
import java.util.Arrays;

@Service
public class BusinessCalculationService {
    private final DataService dataService;

    public BusinessCalculationService(DataService dataService) {
        super();
        this.dataService = dataService;
    }

    public int findMax() {
        return Arrays.stream(dataService.retrieveData())
                .max()
                .orElse(0);
    }
}
