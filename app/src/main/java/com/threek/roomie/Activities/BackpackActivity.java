package com.threek.roomie.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.threek.roomie.Fragments.House.BackpackFragment;
import com.threek.roomie.Fragments.Login.LoginFragment;
import com.threek.roomie.R;

public class BackpackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpack);

        getSupportFragmentManager().beginTransaction().add(R.id.content, new BackpackFragment()).commitNow();
    }
}
