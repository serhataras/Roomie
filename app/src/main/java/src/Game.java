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
    private String pressedButtonId;

    Game(){
        player = new Player();
        gameEnvironment = new GameEnvironment();
        random = new Randomizer();
        events = new Events();
        currentEvent = null;
        pressedButtonId = "";
    }

    public void startGame(){
        //TO DO
    }

    public void terminateGame(){
        //TO DO
    }

    public void activateButton(String buttonId){
        //TO DO
    }

    public void deactivateButton(String buttonId){
        //TO DO
    }

    /*public void restoreStats(){
        //initialize all stats to max stat
        for(int i = 0; i < 4; i++)
            (player.getStats())[i].setStat(Stats.getMaxStat());
    }

    public void refreshStats(Option option){
        //refreshes the stats with respect to the given option
        for(int i = 0; i < 4; i++)
            (player.getStats())[i].setStat((player.getStats()[i].getStat() + option.getEffect()[i]));
    }*/

    public void changeCurrentEvent(){
        //TO DO
    }

    public boolean checkExtremeOption(Option opt){
        return opt.isExtreme();
    }

    public Item generateRandomItem(){
        return random.throwItem();
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    public Randomizer getRandom() {
        return random;
    }

    public void setRandom(Randomizer random) {
        this.random = random;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }

    public String getPressedButtonId() {
        return pressedButtonId;
    }

    public void setPressedButtonId(String pressedButtonId) {
        this.pressedButtonId = pressedButtonId;
    }
}