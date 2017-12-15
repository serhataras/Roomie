package com.threek.roomie.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.threek.roomie.Fragments.House.BackpackFragment;
import com.threek.roomie.Fragments.House.BathroomFragment;
import com.threek.roomie.Fragments.House.BedroomFragment;
import com.threek.roomie.Fragments.House.KitchenFragment;
import com.threek.roomie.Fragments.House.LivingRoomFragment;
import com.threek.roomie.MemoryManager.MemoryManager;
import com.threek.roomie.R;

import java.util.Observable;
import java.util.Observer;

import src.Enums.OptionType;
import src.Enums.StatType;
import src.Game;
import src.Item;
import src.Observable.ObservableEvent;
import src.Observable.ObservableId;
import src.Option;
import src.Stats;

public class HouseActivity extends AppCompatActivity implements Observer
{
    // fragments
    private Fragment currentFragment;
    private KitchenFragment kitchenFragment;
    private BathroomFragment bathroomFragment;
    private BedroomFragment bedroomFragment;
    private LivingRoomFragment livingRoomFragment;

    // ui elements
    private ImageButton playerButton;
    private TextView playerNameText;
    private Button backpackButton;
    private Button outsideButton;

    private Button changeButton;
    private TextView thisRoomText;

    private ProgressBar healthBar;
    private ProgressBar moneyBar;
    private ProgressBar socialityBar;
    private ProgressBar gradesBar;

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);

        game = Game.getInstance();

        game.getPlayer().getBackpack().addItem(new Item("xd", null, new Stats(new int[]{1, 2, 3, 4})));
        game.getPlayer().getBackpack().addItem(new Item("xc", null, new Stats(new int[]{-1, -2, -3, 4})));

        kitchenFragment = new KitchenFragment();
        bathroomFragment = new BathroomFragment();
        bedroomFragment = new BedroomFragment();
        livingRoomFragment = new LivingRoomFragment();

        playerButton = (ImageButton) findViewById(R.id.playerButton);

        playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEventDialog("Do you wanna go out or study at home?");
            }
        });
        playerNameText = (TextView) findViewById(R.id.playerNameText);
        playerNameText.setText(game.getPlayer().getName());

        backpackButton = (Button) findViewById(R.id.backpackButton);
        backpackButton.setOnClickListener(new BackpackListener());

        outsideButton = (Button) findViewById(R.id.muteBox);

        changeButton = (Button) findViewById(R.id.changeRoomButton);
        changeButton.setOnClickListener(new ChangeRoomListener());

        thisRoomText = (TextView) findViewById(R.id.thisRoomText);

        healthBar = (ProgressBar) findViewById(R.id.healthBar);
        moneyBar = (ProgressBar) findViewById(R.id.moneyBar);
        socialityBar = (ProgressBar) findViewById(R.id.socialityBar);
        gradesBar = (ProgressBar) findViewById(R.id.gradesBar);

        // adds all fragments to the activity and shows only the living room fragment
        getSupportFragmentManager().beginTransaction().add(R.id.content, kitchenFragment).commitNow();
        getSupportFragmentManager().beginTransaction().hide(kitchenFragment).commitNow();

        getSupportFragmentManager().beginTransaction().add(R.id.content, bathroomFragment).commitNow();
        getSupportFragmentManager().beginTransaction().hide(bathroomFragment).commitNow();

        getSupportFragmentManager().beginTransaction().add(R.id.content, bedroomFragment).commitNow();
        getSupportFragmentManager().beginTransaction().hide(bedroomFragment).commitNow();

        // living room fragment is shown
        getSupportFragmentManager().beginTransaction().add(R.id.content, livingRoomFragment).commitNow();
        currentFragment = livingRoomFragment;
        thisRoomText.setText(livingRoomFragment.getName());

        // add observers
        game.addObservers(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (game.isGameHasStarted())
        {
            kitchenFragment.addListeners(new ItemListener());
            bathroomFragment.addListeners(new ItemListener());
            bedroomFragment.addListeners(new ItemListener());
            livingRoomFragment.addListeners(new ItemListener());
            game.setGameHasStarted(false);
        }
    }

    // button listener for changing the rooms
    private class ChangeRoomListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            if (currentFragment == livingRoomFragment)
            {
                // hides current fragment
                getSupportFragmentManager().beginTransaction().hide(currentFragment).commitNow();

                // shows the fragment
                getSupportFragmentManager().beginTransaction().show(kitchenFragment).commitNow();
                currentFragment = kitchenFragment;
                thisRoomText.setText(kitchenFragment.getName());
            }
            else if (currentFragment == kitchenFragment)
            {
                // hides current fragment
                getSupportFragmentManager().beginTransaction().hide(currentFragment).commitNow();

                // shows the fragment
                getSupportFragmentManager().beginTransaction().show(bathroomFragment).commitNow();
                currentFragment = bathroomFragment;
                thisRoomText.setText(bathroomFragment.getName());
            }
            else if (currentFragment == bathroomFragment)
            {
                // hides current fragment
                getSupportFragmentManager().beginTransaction().hide(currentFragment).commitNow();

                // shows the fragment
                getSupportFragmentManager().beginTransaction().show(bedroomFragment).commitNow();
                currentFragment = bedroomFragment;
                thisRoomText.setText(bedroomFragment.getName());
            }
            else if (currentFragment == bedroomFragment)
            {
                // hides current fragment
                getSupportFragmentManager().beginTransaction().hide(currentFragment).commitNow();

                // shows the fragment
                getSupportFragmentManager().beginTransaction().show(livingRoomFragment).commitNow();
                currentFragment = livingRoomFragment;
                thisRoomText.setText(livingRoomFragment.getName());
            }
        }
    }

    // listener for backpack button
    private class BackpackListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            Intent intent = new Intent(HouseActivity.this, BackpackActivity.class);
            startActivity(intent);
        }
    }

    // button listener for items in the rooms
    private class ItemListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {

            game.setId(view.getId());
            playerNameText.setText(view.getId() + "");
        }
    }

    // dialog for event question
    private void showEventDialog(String question)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(getString(R.string.ha_question));
        dialog.setMessage(question);
        dialog.setNeutralButton(getString(R.string.ha_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialog.show();
    }

    @Override
    public void update(Observable observable, Object o)
    {
        if (observable == game.getPressedButtonId())
        {
            // if the option is not extreme
            if (!game.checkExtremeOption())
            {
                // get the option type from the current event
                OptionType type = game.whichOption();

                // if the option type is house
                if (type == OptionType.HOUSE_OPTION)
                {
                    // refresh player's stats & change the current event
                    Stats stats = game.chooseHouseOption();
                    updateStatBars(stats);
                }

                // if the option type is night club
                else if (type == OptionType.NIGHT_CLUB_OPTION)
                {
                    // TODO start the night club activity, return the shakeAmount as the result

                    // TODO refresh player's stats & change the current event
                    // TODO change game methods

                    // TODO add a random item
                    // TODO if successful
                    game.addRandomItemToBackPack();
                }

                // if the option type is cafe
                else if (type == OptionType.CAFE_OPTION)
                {
                    // TODO start the cafe activity, return the food choice

                    // TODO refresh player's stats & change the current event
                }

                // if the option type is library
                else if (type == OptionType.LIBRARY_OPTION)
                {
                    // TODO start the library activity, return dB as the result

                    // TODO refresh player's stats & change the current event

                    // TODO if successful
                    game.addRandomItemToBackPack();
                }

                else if (type == OptionType.SCHOOL_OPTION)
                {
                    // TODO if the option type is school

                    // TODO start the school activity, return the answer choice as the result

                    // TODO refresh player's stats & change the current event
                }

                // changes the current event
                game.changeCurrentEvent();
            }

            // if the option is extreme
            else
            {
                // TODO finish the game
            }
        }
        else if (observable == game.getCurrentEvent())
        {
            int id1 = game.getActivatedButtons()[0];
            int id2 = game.getActivatedButtons()[1];

            // deactivate all buttons in the house fragment
            livingRoomFragment.deactivateAllButtons();
            bedroomFragment.deactivateAllButtons();
            bathroomFragment.deactivateAllButtons();
            kitchenFragment.deactivateAllButtons();

            // activate only the event related buttons
            livingRoomFragment.activateButton(id1);
            bedroomFragment.activateButton(id1);
            bathroomFragment.activateButton(id1);
            kitchenFragment.activateButton(id1);

            livingRoomFragment.activateButton(id2);
            bedroomFragment.activateButton(id2);
            bathroomFragment.activateButton(id2);
            kitchenFragment.activateButton(id2);

            // show the question
            showEventDialog(game.getEventQuestion());
        }
    }
    public void updateStatBars(Stats stats)
    {
        moneyBar.setProgress(stats.getStatByIndex(StatType.MONEY));
        gradesBar.setProgress(stats.getStatByIndex(StatType.GRADES));
        socialityBar.setProgress(stats.getStatByIndex(StatType.SOCIALITY));
        healthBar.setProgress(stats.getStatByIndex(StatType.HEALTH));
    }

    public void activateOutsideButton()
    {
        outsideButton.setEnabled(true);
        outsideButton.setAlpha(1f);
    }

    public void deactivateOutsideButton()
    {
        outsideButton.setEnabled(false);
        outsideButton.setAlpha(0.3f);
    }
}
