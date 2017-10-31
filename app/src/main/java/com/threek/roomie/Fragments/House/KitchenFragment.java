package com.threek.roomie.Fragments.House;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.threek.roomie.Activity.HouseActivity;
import com.threek.roomie.R;


public class KitchenFragment extends Fragment {

    // attributes
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_kitchen, container, false);

        return root;
    }

}
