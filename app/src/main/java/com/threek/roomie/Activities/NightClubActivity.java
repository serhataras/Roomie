package com.threek.roomie.Activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.threek.roomie.R;

import src.Cafe;
import src.Enums.OptionType;
import src.FoodItem;
import src.Game;
import src.Hardwares.ShakeDetector;
import src.NightClub;


/**
 * Created by serhat on 13.12.2017.
 */

public class NightClubActivity extends AppCompatActivity {

    // The following are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private NightClub club;

    private Game game;
    private boolean success;
    private long timer=0;
    public final int MAX_TIME=20;

    private final String TAG= NightClubActivity.class.getSimpleName();

    private TextView countDisplay,timerDisplay,clubTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nightclub);

        game = game.getInstance();
        NightClub club = (NightClub) game.getGameEnvironment().getOutdoorEnvironment(OptionType.NIGHT_CLUB_OPTION);//Holds the temporary NightClub of the singleton Game object

        countDisplay = (TextView) findViewById(R.id.countView);
        countDisplay.setText("0");

        timerDisplay = (TextView) findViewById(R.id.timeView);
        timerDisplay.setText(MAX_TIME + " second to dance!");

        clubTag = (TextView) findViewById(R.id.clubName);
        clubTag.setText("Bilkent Night Club");
        club.setShakeAmount(0);
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.determineLevel(club.getMAX_SHAKE()/10);
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(final int count) {

                /*
				 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */
                //TODO time interval
                Handler handler = new Handler();
                new CountDownTimer(MAX_TIME*1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        timer=millisUntilFinished / 1000;
                        updateView();
                        //if threshold passed, dont need to dance anymore
                        if (mShakeDetector.get_mShakeCount()>mShakeDetector.getShakeAmount())
                        {
                            ((NightClub) game.getGameEnvironment().getOutdoorEnvironment(OptionType.NIGHT_CLUB_OPTION)).setShakeAmount(mShakeDetector.get_mShakeCount());
                            finish();
                        }
                    }
                    public void onFinish() {
                        timerDisplay.setText("done!");
                        //the game instance updated
                        ((NightClub) game.getGameEnvironment().getOutdoorEnvironment(OptionType.NIGHT_CLUB_OPTION)).setShakeAmount(mShakeDetector.get_mShakeCount());
                        finish();
                    }
                }.start();


            }
        });
        Button exitButton = (Button)findViewById(R.id.clubExit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }
    public void updateView(){
        if(mShakeDetector.get_mShakeCount() > 0) {
            countDisplay.setText(String.valueOf(mShakeDetector.get_mShakeCount()));
        }
        else
            countDisplay.setText("0");
    }
    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
