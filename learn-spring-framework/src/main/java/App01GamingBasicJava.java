import com.in28minutes.learn_spring_framework.game.GameRunner;
import com.in28minutes.learn_spring_framework.game.PacmanGame;

public class App01GamingBasicJava {

    public static void main(String[] args) {

//        var game = new MarioGame();
//        var game = new superContraGame();
        var game = new PacmanGame();
        var gameRunner = new GameRunner(game);
        // Game은 GameRunner의 의존성(Dependency)이다
        gameRunner.run();
    }


}
