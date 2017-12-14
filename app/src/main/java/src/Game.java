package src;
import java.util.ArrayList;

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
        currentEvent = events.getStart();
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

    public void activateButton(int buttonId)
    {
        House houseToSearch = gameEnvironment.getHouse();
        for (int i = 0; i < House.NUMBER_OF_ROOMS; i++)
        {
            for (int j = 0; j < Room.NUMBER_OF_ITEMS; j++)
            {
                int itemId = houseToSearch.getRooms()[i].getItems()[j].getId();
                if (itemId == buttonId)
                {
                    houseToSearch.getRooms()[i].getItems()[j].setClickable(true);
                    return;
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
                houseToSearch.getRooms()[i].getItems()[j].setClickable(false);
            }
        }
    }

    public void refreshStats(Stats stats)
    {
        player.updateStats(stats);
    }

    public void changeCurrentEvent(boolean direction)
    {
        Option[] options = currentEvent.getOptionArray();
        //option 1
        if(direction == true){
            if(!options[0].isExtreme())
                currentEvent = events.getLeft(currentEvent);
        }
        //option 2
        else
        {
            if(!options[1].isExtreme())
                currentEvent = events.getRight(currentEvent);
        }
    }

    public boolean checkExtremeOption(int buttonId)
    {
        return currentEvent.isOptionExtreme(buttonId);
    }

    public Item addRandomItemToBackPack()
    {
        Item item = random.throwItem();
        player.getBackpack().addItem(item);
        return item;
    }

    public String sendEventQuestion()
    {
        return currentEvent.getQuestion();
    }

    public Stats chooseHouseOption(int buttonId)
    {
        Stats stats = currentEvent.chooseAnOption(buttonId).getEffect();
        refreshStats(stats);
        return stats;
    }

    public Stats chooseOutdoorOption(int buttonId, boolean success)
    {
        Stats stats = currentEvent.chooseAnOption(buttonId).getEffect();
        if (success)
        {
            refreshStats(stats);
            addRandomItemToBackPack();
            return stats;
        }
        return null;
    }

    public ArrayList<Item> getBackPackItems()
    {
        return player.getBackpack().getItemList();
    }

    public int[] getActivatedButtons()
    {
        return currentEvent.getOptionsId();
    }

    public OptionType whichOption(int buttonId)
    {
        return currentEvent.whichOption(buttonId);
    }

    public QuizQuestion sendQuizQuestion()
    {
        return ((School) gameEnvironment.getOutdoorEnvironment(OutdoorEnvironment.SCHOOL_ENVIRONEMENT)).getRandomQuestion();
    }

    public FoodItem[] getFoodMenu()
    {
        return ((Cafe) gameEnvironment.getOutdoorEnvironment(OutdoorEnvironment.CAFE_ENVIRONMENT)).getMenu();
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

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public int getPressedButtonId() {
        return pressedButtonId;
    }

    public static void main(String[] args){
        Game g= new Game();
        g.changeCurrentEvent(true);
        System.out.println(g.getCurrentEvent() + "!");
        g.changeCurrentEvent(false);
        System.out.println(g.getCurrentEvent() + "!");
        g.changeCurrentEvent(false);
        System.out.println(g.getCurrentEvent() + "!");
    }
}