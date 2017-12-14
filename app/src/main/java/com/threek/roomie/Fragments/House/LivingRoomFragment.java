package com.threek.roomie.Fragments.House;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.threek.roomie.R;

import src.Game;
import src.House;
import src.LivingRoomItems;
import src.RoomType;

/**
 * A simple {@link Fragment} subclass.
 */
public class LivingRoomFragment extends Fragment
{
    private String name;
    private Game game;

    // attributes
    private ImageButton[] buttons;

    public LivingRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        buttons = new ImageButton[4];
        name = "Living room";
        game = Game.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_living_room, container, false);

        buttons[LivingRoomItems.TV.ordinal()] = (ImageButton) root.findViewById(R.id.tv);
        buttons[LivingRoomItems.SOFA.ordinal()] = (ImageButton) root.findViewById(R.id.sofa);
        buttons[LivingRoomItems.PLANTS.ordinal()] = (ImageButton) root.findViewById(R.id.plants);
        buttons[LivingRoomItems.BIGTABLE.ordinal()] = (ImageButton) root.findViewById(R.id.bigtable);

        buttons[LivingRoomItems.TV.ordinal()].setId(House.TV_ID);
        buttons[LivingRoomItems.SOFA.ordinal()].setId(House.SOFA_ID);
        buttons[LivingRoomItems.PLANTS.ordinal()].setId(House.PLANTS_ID);
        buttons[LivingRoomItems.BIGTABLE.ordinal()].setId(House.BIGTABLE_ID);

        return root;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addListeners(View.OnClickListener listener)
    {
        for (int i = 0; i < 4; i++)
            buttons[i].setOnClickListener(listener);
    }

    public boolean activateButton(int id)
    {
        for (int i = 0; i < 4; i++)
        {
            if (buttons[i].getId() == id)
            {
                buttons[i].setEnabled(true);
                buttons[i].setAlpha(1f);
                return true;
            }
        }
        return false;
    }

    public void deactivateAllButtons()
    {
        for (int i = 0; i < 4; i++)
        {
            buttons[i].setEnabled(false);
            buttons[i].setAlpha(0.3f);
        }
    }
}
