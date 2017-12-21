package com.threek.roomie.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.threek.roomie.R;

import src.Enums.OptionType;
import src.Game;
import src.Hardwares.SoundMeter;
import src.Library;

/**
 * Created by serhat on 13.12.2017.
 */

public class LibraryActivity extends AppCompatActivity {

    private Game game;
    private int currentDb;
    private double Max_DB;
    private TextView timeView;
    private SoundMeter soundMeter;
    private Library library;
    private boolean threshold=true;
    private Button exitButton;
    //Declare timer
    CountDownTimer cTimer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        game = Game.getInstance();
        timeView= (TextView)findViewById(R.id.timeView);
        library=((Library)game.getGameEnvironment().getOutdoorEnvironment(OptionType.LIBRARY_OPTION));
        soundMeter=new SoundMeter();
        Max_DB=library.getMAX_DB();
        //Microphone implementation
        //TODO
        //current db will always change with the SAMPLE RATE, it's more easy to check is threshold exceeded

        //each getMaxAmplitude Call, this will executed along with others
        ((Library)game.getGameEnvironment().getOutdoorEnvironment(OptionType.LIBRARY_OPTION)).setCurrentDb(currentDb);

        //1'st check the if currentDb(threshold) exceeds MaxDB
        //((Library)game.getGameEnvironment().getOutdoorEnvironment(OptionType.LIBRARY_OPTION)).isThresholdExceeded();


        //will be called at the home activity decide whether the currentDB ever gets higher than the MAX_DB
        //((Library)game.getGameEnvironment().getOutdoorEnvironment(OptionType.LIBRARY_OPTION)).isChallengeSuccess();

        exitButton = (Button)findViewById(R.id.libraryExit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //TODO time interval
        Handler handler = new Handler();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    SoundMeter.RECORD_AUDIO);

        } else {
            soundMeter.start();
        }
        startTimer();
        soundMeter.stop();

//comment1:
    }
    //start timer function
    void startTimer() {
        soundMeter.start();
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cTimer = new CountDownTimer(10000, 10) {
            public void onTick(long millisUntilFinished) {
                //current db bug
                ((Library)game.getGameEnvironment().getOutdoorEnvironment(OptionType.LIBRARY_OPTION)).setCurrentDb(soundMeter.getAmplitudeEMA());
                timeView.setText(""+((Library)game.getGameEnvironment().getOutdoorEnvironment(OptionType.LIBRARY_OPTION)).getCurrentDb());
                Log.i(this.getClass().getName(),""+soundMeter.getAmplitude());
                if(((Library)game.getGameEnvironment().getOutdoorEnvironment(OptionType.LIBRARY_OPTION)).isThresholdExceeded()) {
                    ((Library)game.getGameEnvironment().getOutdoorEnvironment(OptionType.LIBRARY_OPTION)).setChallengeSuccess(false);
                    threshold=true;
                }
            }
            public void onFinish() {
                finish();

            }

        };
        cTimer.start();
    }
    //cancel timer
    void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }
}
//comment1:
//        new CountDownTimer(5000, 50) {
//            public void onTick(long millisUntilFinished) {
//                library.setCurrentDb(soundMeter.getAmplitude());
//                timeView.setText("");
//                if(library.isThresholdExceeded()) {
//                    library.setChallengeSuccess(false);
//                    threshold=true;
//                    finish();
//                }
//            }
//            public void onFinish() {
//
//            }
//        }.start();
//
//
