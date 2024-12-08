package com.in28minutes.learn_spring_framework;

import com.in28minutes.learn_spring_framework.game.GamingConsole;
import com.in28minutes.learn_spring_framework.game.GameRunner;

import com.in28minutes.learn_spring_framework.game.PacmanGame;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GamingConfiguration {

    @Bean
    public GamingConsole game() {
            var game = new PacmanGame();
            return game;
    }

    @Bean
    public GameRunner gameRunner(GamingConsole game) {
        var gameRunner = new GameRunner(game);
        return gameRunner;
    }


//    var game = new PacmanGame();
//    var gameRunner = new GameRunner(game);
//    // Game은 GameRunner의 의존성(Dependency)이다
//        gameRunner.run();

}
