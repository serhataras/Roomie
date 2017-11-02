package src;

import android.text.style.TtsSpan;

/**
 * Created by eliztekcan on 2.11.2017.
 */

public class Game {

    Player player;
    GameEnvironment gameEnvironment;
    Randomizer random;
    Events events;
    Event currentEvent;
    String pressedButtonId;

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

    public void restoreStats(){
        //initialize all stats to max stat
        for(int i = 0; i < 4; i++)
        (player.getStats())[i].setStat(Stats.MAX_STAT);
    }

    public void refreshStats(Option option){
        //refreshes the stats with respect to the given option
        for(int i = 0; i < 4; i++)
            (player.getStats())[i].setStat((player.getStats()[i].getStat() + option.getEffect()[i]));
    }

    public void changeCurrentEvent(){
        //TO DO
    }

    public boolean checkExtremeOption(){
        return (currentEvent.getOptionArray())[0].isExtreme
    }


}
