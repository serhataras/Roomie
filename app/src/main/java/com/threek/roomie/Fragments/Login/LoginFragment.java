package com.threek.roomie.Fragments.Login;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.threek.roomie.Activity.HouseActivity;
import com.threek.roomie.R;

public class LoginFragment extends Fragment {

    // attributes
    private RadioButton maleButton, femaleButton;
    private EditText nameInput;
    private Button startGameButton;

    private String name;
    private boolean gender; // female = false, male = true

    public LoginFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init
        name = "";
        gender = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        maleButton = (RadioButton) root.findViewById(R.id.maleButton);
        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = true;

                // set other button unchecked
                femaleButton.setChecked(false);
            }
        });

        femaleButton = (RadioButton) root.findViewById(R.id.femaleButton);
        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = false;

                // set other button unchecked
                maleButton.setChecked(false);
            }
        });

        nameInput = (EditText) root.findViewById(R.id.nameInput);
        startGameButton = (Button) root.findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(new startGameButtonListener());
        return root;
    }

    // inner class for startGame button
    private class startGameButtonListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            // start game after clicking the button by calling house activity
            Intent intent = new Intent(getActivity(), HouseActivity.class);
            saveProperties();
            startActivity(intent);
        }
    }

    // method for saving properties to the device
    private void saveProperties()
    {
        // TODO implement the method
    }
}
