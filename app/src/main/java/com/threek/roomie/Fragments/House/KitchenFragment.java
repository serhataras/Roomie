package com.threek.roomie.Fragments.House;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.threek.roomie.Activity.HouseActivity;
import com.threek.roomie.R;


public class KitchenFragment extends Fragment {

    // attributes
    private String name;

    private ImageButton[] buttons;

    public KitchenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        buttons = new ImageButton[4];
        name = "Kitchen Fragment";
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

        return root;
    }

    public String getName() {
        return name;
    }
}
