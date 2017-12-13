package src;
/**
 * Created by eliztekcan on 2.11.2017.
 */

public class Game {

    private Player player;
    private GameEnvironment gameEnvironment;
    private Randomizer random;
    private Events events;
    private Event currentEvent;
    private int pressedButtonId;

    // singleton object
    private static Game instance = null;

    // singleton constructor
    private Game()
    {
        player = new Player();
        gameEnvironment = new GameEnvironment();
        random = new Randomizer();
        events = new Events();
        currentEvent = null;
        pressedButtonId = -1;
    }

    // method for getting singleton game instance
    public static Game getInstance()
    {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }


}