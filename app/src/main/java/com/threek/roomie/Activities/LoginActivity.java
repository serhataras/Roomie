package com.threek.roomie.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.threek.roomie.Fragments.Login.LoginFragment;
import com.threek.roomie.R;

import src.Game;

public class LoginActivity extends AppCompatActivity
{
    private static Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("");

        game = Game.getInstance();
        startGame();

        getSupportFragmentManager().beginTransaction().add(R.id.content, new LoginFragment()).commitNow();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // recreate game when house activity returns to here
        if (!game.isGameHasStarted())
        {
            reCreateGame();
            startGame();
        }
    }

    private void startGame()
    {
        game.initializeGameObjects(getResources(), LoginActivity.this.getPackageName());
    }

    private void reCreateGame()
    {
        game.reCreateGame();
    }
}
