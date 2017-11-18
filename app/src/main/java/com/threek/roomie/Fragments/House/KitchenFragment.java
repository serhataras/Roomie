package com.threek.roomie.Fragments.House;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.threek.roomie.R;


public class KitchenFragment extends Fragment {

    // attributes
    private static final String name = "Kitchen";

    private ImageButton[] buttons;

    public KitchenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        buttons = new ImageButton[4];
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_kitchen, container, false);

        buttons[0] = (ImageButton) root.findViewById(R.id.item1);
        buttons[1] = (ImageButton) root.findViewById(R.id.item2);
        buttons[2] = (ImageButton) root.findViewById(R.id.item3);
        buttons[3] = (ImageButton) root.findViewById(R.id.item4);


        buttons[2].setEnabled(false);
        buttons[2].setAlpha(0.5f);
        buttons[1].setEnabled(false);
        buttons[1].setAlpha(0.5f);
        return root;
    }

    public String getName() {
        return name;
    }

    public void addListeners(View.OnClickListener listener)
    {
        for (int i = 0; i < 4; i++)
            buttons[i].setOnClickListener(listener);
    }

}
