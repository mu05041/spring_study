package com.in28minutes.learn_spring_framework.game;

public class PacmanGame implements GamingConsole {
    public void up() {
        System.out.println("up");
    }
    public void down() {
        System.out.println("Go down");
    }
    public void left() {
        System.out.println("Go back");
    }
    public void right() {
        System.out.println("Go right");
    }
}
