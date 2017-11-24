package com.threek.roomie.Fragments.House;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.threek.roomie.R;

import java.util.ArrayList;

import src.Item;

/**
 * A simple {@link Fragment} subclass.
 */
public class BackpackFragment extends Fragment {

    private ArrayList<Item> itemList;


    public BackpackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_backpack, container, false);
    }


}
