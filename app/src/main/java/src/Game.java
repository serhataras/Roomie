package src;

import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.Observer;

import src.Enums.OptionType;
import src.Enums.StatType;
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
    private boolean gameOver;

    // singleton object
    private static Game instance = null;

    // singleton constructor
    private Game()
    {
        player = new Player();
        currentEvent = new ObservableEvent(null);
        pressedButtonId = new ObservableId(-1);
        gameHasStarted = true;
        gameOver = false;
    }

    // method for getting singleton game instance
    public static Game getInstance()
    {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void reCreateGame()
    {
        player = new Player();
        currentEvent = new ObservableEvent(null);
        pressedButtonId = new ObservableId(-1);
        gameHasStarted = true;
        gameOver = false;
    }

    public void initializeGameObjects(Resources r, String pn)
    {
        initializeEvents(r, pn);
        initializeGameEnvironment(r, pn);
        initializeRandomizer(r, pn);
    }

    private void initializeEvents(Resources r, String pn)
    {
        events = new Events(r, pn);
        currentEvent.setValue(events.getStart());
        Log.e("CEEEEEEEEE", currentEvent.getValue().toString());
    }

    private void initializeGameEnvironment(Resources r, String pn)
    {
        gameEnvironment = new GameEnvironment(r, pn);
    }

    private void initializeRandomizer(Resources r, String pn){
        random = new Randomizer(r, pn);
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

    private void refreshStats(Stats stats)
    {
        player.updateStats(stats);
    }

    public void changeCurrentEvent()
    {
        Option[] options = (currentEvent.getValue()).getOptionArray();
        //option 1
        if(pressedButtonId.getValue() == options[0].getId()){
            if(!options[0].isExtreme())
                currentEvent.setValue(events.getLeft(currentEvent.getValue()));
            else
                gameOver = true;
        }
        //option 2
        else if (pressedButtonId.getValue() == options[1].getId())
        {
            if(!options[1].isExtreme())
                currentEvent.setValue(events.getLeft(currentEvent.getValue()));
            else
                gameOver = true;

        }
    }

    public boolean isGameOver()
    {
        if (currentEvent == null || gameOver)
        {
            gameOver = true;
        }
        return gameOver;
    }

    public boolean checkExtremeOption()
    {
        return currentEvent.getValue().isOptionExtreme(pressedButtonId.getValue());
    }

    private Item addRandomItemToBackPack()
    {
        Item item = random.throwItem();
        player.getBackpack().addItem(item);
        return item;
    }

    public String sendEventQuestion()
    {
        return currentEvent.getValue().getQuestion();
    }

    public void chooseHouseOption()
    {
        Option option = currentEvent.getValue().chooseAnOption(pressedButtonId.getValue());

        if (option != null && option.getOptionType() == OptionType.HOUSE_OPTION)
        {
            Stats stats = currentEvent.getValue().chooseAnOption(pressedButtonId.getValue()).getEffect();
            refreshStats(stats);
        }
    }

    public void chooseNightClubOption()
    {
        // get the option from the event
        Option option = currentEvent.getValue().chooseAnOption(pressedButtonId.getValue());

        // if the option is night club option
        if (option != null && option.getOptionType() == OptionType.NIGHT_CLUB_OPTION)
        {
            // update challenge success
            getGameEnvironment().getOutdoorEnvironment(OptionType.NIGHT_CLUB_OPTION).updateChallengeSuccess();

            Log.e("SUCC", getGameEnvironment().getOutdoorEnvironment(OptionType.NIGHT_CLUB_OPTION).isChallengeSuccess() + "");
            // if challenge is successful, update stats & add random item
            if (getGameEnvironment().getOutdoorEnvironment(OptionType.NIGHT_CLUB_OPTION).isChallengeSuccess())
            {
                Stats stats = option.getEffect();
                refreshStats(stats);
                addRandomItemToBackPack();
            }
            else
            {
                // TODO what happens if challenge is not successful
            }
        }
    }

    public void chooseCafeOption()
    {
        Option option = currentEvent.getValue().chooseAnOption(pressedButtonId.getValue());

        if (option != null && option.getOptionType() == OptionType.CAFE_OPTION)
        {
            // get the chosen food item
            FoodItem food = ((Cafe) getGameEnvironment().getOutdoorEnvironment(OptionType.CAFE_OPTION)).getSelectedFood();

            int priceOfTheFood = food.getPrice();
            int healthOfTheFood = food.getHealth();

            // update stats
            getPlayer().updateStatByIndex(StatType.HEALTH, healthOfTheFood);
            getPlayer().updateStatByIndex(StatType.MONEY, -1 * priceOfTheFood);

            Stats stats = option.getEffect();
            refreshStats(stats);
            addRandomItemToBackPack();
        }
    }

    public void chooseSchoolOption()
    {
        Option option = currentEvent.getValue().chooseAnOption(pressedButtonId.getValue());

        if (option != null && option.getOptionType() == OptionType.SCHOOL_OPTION)
        {
            // update challenge success
            getGameEnvironment().getOutdoorEnvironment(OptionType.SCHOOL_OPTION).updateChallengeSuccess();

            // if challenge is successful, update stats & add random item
            if (getGameEnvironment().getOutdoorEnvironment(OptionType.SCHOOL_OPTION).isChallengeSuccess())
            {
                // increase the grades
                QuizQuestion question = ((School) getGameEnvironment().getOutdoorEnvironment(OptionType.SCHOOL_OPTION)).getCurrentRandomQuestion();
                player.updateStatByIndex(StatType.GRADES, question.getGrades());

                Stats stats = option.getEffect();
                refreshStats(stats);
                addRandomItemToBackPack();
            }
            else
            {
                // if not successful decrease the grades
                QuizQuestion question = ((School) getGameEnvironment().getOutdoorEnvironment(OptionType.SCHOOL_OPTION)).getCurrentRandomQuestion();
                player.updateStatByIndex(StatType.GRADES, -1 * question.getGrades());
            }
        }
    }

    public void chooseLibraryOption()
    {
        Option option = currentEvent.getValue().chooseAnOption(pressedButtonId.getValue());

        if (option != null && option.getOptionType() == OptionType.LIBRARY_OPTION)
        {
            // update challenge success
            getGameEnvironment().getOutdoorEnvironment(OptionType.LIBRARY_OPTION).updateChallengeSuccess();

            // if challenge is successful, update stats & add random item
            if (getGameEnvironment().getOutdoorEnvironment(OptionType.LIBRARY_OPTION).isChallengeSuccess())
            {
                Stats stats = option.getEffect();
                refreshStats(stats);
                addRandomItemToBackPack();
            }
            else
            {
                // TODO what happens if challenge is not successful
            }
        }
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

    public void addObservers(Observer observer)
    {
        pressedButtonId.addObserver(observer);
        currentEvent.addObserver(observer);
    }

    public String getEventQuestion()
    {
        return currentEvent.getValue().getQuestion();
    }
}