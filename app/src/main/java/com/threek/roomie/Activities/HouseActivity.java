package com.threek.roomie.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
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
import src.House;
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

    private Button changeButton;
    private TextView thisRoomText;

    private ProgressBar healthBar;
    private ProgressBar moneyBar;
    private ProgressBar socialityBar;
    private ProgressBar gradesBar;

    private Button schoolButton;
    private Button libraryButton;
    private Button nightClubButton;
    private Button cafeButton;

    // game instance
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);

        // game instance to work on
        game = Game.getInstance();
        game.initializeEvents(getResources(), HouseActivity.this.getPackageName());
        game.initializeRandomizer(getResources(), HouseActivity.this.getPackageName());
        game.initializeGameEnvironment(getResources(), HouseActivity.this.getPackageName());

        // house fragments
        kitchenFragment = new KitchenFragment();
        bathroomFragment = new BathroomFragment();
        bedroomFragment = new BedroomFragment();
        livingRoomFragment = new LivingRoomFragment();

        playerButton = (ImageButton) findViewById(R.id.playerButton);
        playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                showEventDialog(game.sendEventQuestion());
            }
        });

        playerNameText = (TextView) findViewById(R.id.playerNameText);
        playerNameText.setText(game.getPlayer().getName());

        // backpack button
        backpackButton = (Button) findViewById(R.id.backpackButton);
        backpackButton.setOnClickListener(new BackpackListener());

        // outside buttons
        schoolButton = (Button) findViewById(R.id.schoolButton);
        schoolButton.setOnClickListener(new ItemListener());
        schoolButton.setId(House.idGenerator(OptionType.SCHOOL_OPTION, null, null));

        libraryButton = (Button) findViewById(R.id.libraryButton);
        libraryButton.setOnClickListener(new ItemListener());
        libraryButton.setId(House.idGenerator(OptionType.LIBRARY_OPTION, null, null));

        nightClubButton = (Button) findViewById(R.id.nightClubButton);
        nightClubButton.setOnClickListener(new ItemListener());
        nightClubButton.setId(House.idGenerator(OptionType.NIGHT_CLUB_OPTION, null, null));

        cafeButton = (Button) findViewById(R.id.cafeButton);
        cafeButton.setOnClickListener(new ItemListener());
        cafeButton.setId(House.idGenerator(OptionType.CAFE_OPTION, null, null));

        changeButton = (Button) findViewById(R.id.changeRoomButton);
        changeButton.setOnClickListener(new ChangeRoomListener());

        thisRoomText = (TextView) findViewById(R.id.thisRoomText);

        healthBar = (ProgressBar) findViewById(R.id.healthBar);
        moneyBar = (ProgressBar) findViewById(R.id.moneyBar);
        socialityBar = (ProgressBar) findViewById(R.id.socialityBar);
        gradesBar = (ProgressBar) findViewById(R.id.gradesBar);
        updateStatBars();

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

        // add observers to the game
        game.addObservers(this);

        // initialize the stat bars
        updateStatBars();
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
            prepareButtonsForTheCurrentEvent();
            game.setGameHasStarted(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStatBars();
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
            if (game.checkExtremeOption() == false)
            {
                // get the option type from the current event
                OptionType type = game.whichOption();
                Log.e("Type", type.toString());

                // if the option type is house
                if (type == OptionType.HOUSE_OPTION)
                {
                    Log.e("Obs", "House");
                    // choose house option
                    game.chooseHouseOption();
                }

                // if the option type is night club
                else if (type == OptionType.NIGHT_CLUB_OPTION)
                {
                    Log.e("Obs", "Disco");
                    // start the night club activity
                    Intent intent = new Intent(HouseActivity.this, NightClubActivity.class);
                    startActivity(intent);

                    // choose nightclub option
                    game.chooseNightClubOption();
                }

                // if the option type is cafe
                else if (type == OptionType.CAFE_OPTION)
                {
                    Log.e("Obs", "Cafe");
                    // start the cafe activity
                    Intent intent = new Intent(HouseActivity.this, CafeActivity.class);
                    startActivity(intent);

                    // choose cafe option
                    game.chooseCafeOption();
                }

                // if the option type is library
                else if (type == OptionType.LIBRARY_OPTION)
                {
                    Log.e("Obs", "Library");
                    // start the library activity
                    Intent intent = new Intent(HouseActivity.this, LibraryActivity.class);
                    startActivity(intent);

                    // choose library option
                    game.chooseLibraryOption();
                }

                else if (type == OptionType.SCHOOL_OPTION)
                {
                    // start the school activity
                    Intent intent = new Intent(HouseActivity.this, SchoolActivity.class);
                    startActivity(intent);

                    // choose house option
                    game.chooseSchoolOption();
                }

                // update stat bars and change the current event
                updateStatBars();
                game.changeCurrentEvent();
            }

            // if the option is extreme
            else
            {
                showEventDialog("GAME OVER!");
            }
        }
        else if (observable == game.getCurrentEvent())
        {

                prepareButtonsForTheCurrentEvent();

        }
    }
    public void updateStatBars()
    {
        moneyBar.setProgress(game.getPlayer().getStats().getStatByIndex(StatType.MONEY));
        gradesBar.setProgress(game.getPlayer().getStats().getStatByIndex(StatType.GRADES));
        socialityBar.setProgress(game.getPlayer().getStats().getStatByIndex(StatType.SOCIALITY));
        healthBar.setProgress(game.getPlayer().getStats().getStatByIndex(StatType.HEALTH));
    }

    public void activateOutsideButtons()
    {
        int id1 = game.getActivatedButtons()[0];
        int id2 = game.getActivatedButtons()[1];

        if (schoolButton.getId() == id1 || schoolButton.getId() == id2)
        {
            schoolButton.setEnabled(true);
            schoolButton.setAlpha(1f);
        }
        else if (libraryButton.getId() == id1 || libraryButton.getId() == id2)
        {
            libraryButton.setEnabled(true);
            libraryButton.setAlpha(1f);
        }
        else if (nightClubButton.getId() == id1 || nightClubButton.getId() == id2)
        {
            nightClubButton.setEnabled(true);
            nightClubButton.setAlpha(1f);

        }
        else if (cafeButton.getId() == id1 || cafeButton.getId() == id2)
        {
            cafeButton.setEnabled(true);
            cafeButton.setAlpha(1f);
        }
    }

    public void deactivateOutsideButtons()
    {
        schoolButton.setEnabled(false);
        schoolButton.setAlpha(0.3f);

        libraryButton.setEnabled(false);
        libraryButton.setAlpha(0.3f);

        nightClubButton.setEnabled(false);
        nightClubButton.setAlpha(0.3f);

        cafeButton.setEnabled(false);
        cafeButton.setAlpha(0.3f);
    }

    public void activateHouseButtons()
    {
        int id1 = game.getActivatedButtons()[0];
        int id2 = game.getActivatedButtons()[1];

        livingRoomFragment.activateButton(id1);
        bedroomFragment.activateButton(id1);
        bathroomFragment.activateButton(id1);
        kitchenFragment.activateButton(id1);

        livingRoomFragment.activateButton(id2);
        bedroomFragment.activateButton(id2);
        bathroomFragment.activateButton(id2);
        kitchenFragment.activateButton(id2);
    }

    public void deactivateHouseButtons()
    {
        livingRoomFragment.deactivateAllButtons();
        bedroomFragment.deactivateAllButtons();
        bathroomFragment.deactivateAllButtons();
        kitchenFragment.deactivateAllButtons();
    }

    public void prepareButtonsForTheCurrentEvent()
    {
        // first deactivate
        deactivateHouseButtons();
        deactivateOutsideButtons();

        // then activate
        activateHouseButtons();
        activateOutsideButtons();
    }
}
