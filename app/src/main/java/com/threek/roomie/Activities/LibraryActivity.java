package com.threek.roomie.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.threek.roomie.R;

import src.Enums.OptionType;
import src.Game;
import src.Library;

/**
 * Created by serhat on 13.12.2017.
 */

public class LibraryActivity extends AppCompatActivity {

    private Game game;
    private int currentDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        game=game.getInstance();

        //Microphone implementation
        //TODO
        //current db will always change with the SAMPLE RATE, it's more easy to check is threshold exceeded

        //each getMaxAmplitude Call, this will executed along with others
        ((Library)game.getGameEnvironment().getOutdoorEnvironment(OptionType.LIBRARY_OPTION)).setCurrentDb(currentDb);

        //1'st check the if currentDb(threshold) exceeds MaxDB
        ((Library)game.getGameEnvironment().getOutdoorEnvironment(OptionType.LIBRARY_OPTION)).isThresholdExceeded();


        //will be called at the home activity decide whether the currentDB ever gets higher than the MAX_DB
        ((Library)game.getGameEnvironment().getOutdoorEnvironment(OptionType.LIBRARY_OPTION)).isChallengeSuccess();
    }
}
