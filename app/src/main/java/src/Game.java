package src;

import java.util.ArrayList;

import src.Enums.OptionType;
import src.Observable.ObservableEvent;
import src.Observable.ObservableId;

/**
 * Created by eliztekcan on 2.11.2017.
 */

public class Game
{
    private Player player;
    private GameEnvironment gameEnvironment;
    private Randomizer random;
    private Events events;
    private ObservableEvent currentEvent;
    private ObservableId pressedButtonId;
    private boolean gameHasStarted;

    // singleton object
    private static Game instance = null;

    // singleton constructor
    private Game()
    {
        player = new Player();
        gameEnvironment = new GameEnvironment();
        random = new Randomizer();
        events = new Events();
        currentEvent = new ObservableEvent(null);
        pressedButtonId = new ObservableId(-1);
        gameHasStarted = true;
    }

    // method for getting singleton game instance
    public static Game getInstance()
    {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void activateButton()
    {
        int[] options = currentEvent.getValue().getOptionsId();

        House houseToSearch = gameEnvironment.getHouse();
        for (int i = 0; i < House.NUMBER_OF_ROOMS; i++)
        {
            for (int j = 0; j < Room.NUMBER_OF_ITEMS; j++)
            {
                if (houseToSearch.getRooms()[i] != null && houseToSearch.getRooms()[i].getItems()[j] != null)
                {
                    int itemId = houseToSearch.getRooms()[i].getItems()[j].getId();
                    if (itemId == options[0] || itemId == options[1])
                    {
                        houseToSearch.getRooms()[i].getItems()[j].setClickable(true);
                    }
                }
            }
        }
    }

    public void deactivateAllButtons()
    {
        House houseToSearch = gameEnvironment.getHouse();

        for (int i = 0; i < House.NUMBER_OF_ROOMS; i++)
        {
            for (int j = 0; j < Room.NUMBER_OF_ITEMS; j++)
            {
                if (houseToSearch.getRooms()[i] != null && houseToSearch.getRooms()[i].getItems()[j] != null)
                {
                    houseToSearch.getRooms()[i].getItems()[j].setClickable(false);
                }
            }
        }
    }

    public void refreshStats(Stats stats)
    {
        player.updateStats(stats);
    }

    public void changeCurrentEvent()
    {
        // TODO pressedButtonId to change the event
    }

    public boolean checkExtremeOption()
    {
        return currentEvent.getValue().isOptionExtreme(pressedButtonId.getValue());
    }

    public Item addRandomItemToBackPack()
    {
        Item item = random.throwItem();
        player.getBackpack().addItem(item);
        return item;
    }

    public String sendEventQuestion()
    {
        return currentEvent.getValue().getQuestion();
    }

    public Stats chooseHouseOption()
    {
        Stats stats = currentEvent.getValue().chooseAnOption(pressedButtonId.getValue()).getEffect();
        refreshStats(stats);
        return stats;
    }

    public Stats chooseOutdoorOption(boolean success)
    {
        Stats stats = currentEvent.getValue().chooseAnOption(pressedButtonId.getValue()).getEffect();
        if (success)
        {
            refreshStats(stats);
            addRandomItemToBackPack();
            return stats;
        }

        stats.makeStatsZero();
        return stats;
    }

    public ArrayList<Item> getBackPackItems()
    {
        return player.getBackpack().getItemList();
    }

    public int[] getActivatedButtons()
    {
        return currentEvent.getValue().getOptionsId();
    }

    public OptionType whichOption()
    {
        return currentEvent.getValue().whichOption(pressedButtonId.getValue());
    }

    public QuizQuestion sendQuizQuestion()
    {
        return ((School) gameEnvironment.getOutdoorEnvironment(OptionType.SCHOOL_OPTION)).getRandomQuestion();
    }

    public FoodItem[] getFoodMenu()
    {
        return ((Cafe) gameEnvironment.getOutdoorEnvironment(OptionType.CAFE_OPTION)).getMenu();
    }

    public Player getPlayer() {
        return player;
    }

    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    public Randomizer getRandom() {
        return random;
    }

    public Events getEvents() {
        return events;
    }

    public ObservableEvent getCurrentEvent()
    {
        return currentEvent;
    }

    public ObservableId getPressedButtonId() {
        return pressedButtonId;
    }

    public boolean isGameHasStarted() {
        return gameHasStarted;
    }

    public void setGameHasStarted(boolean gameHasStarted) {
        this.gameHasStarted = gameHasStarted;
    }

    public void setId(int id)
    {
        pressedButtonId.setValue(id);
    }
}