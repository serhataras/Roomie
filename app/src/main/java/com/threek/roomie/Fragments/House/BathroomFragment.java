package com.threek.roomie.Fragments.House;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.threek.roomie.R;

import src.BathroomItems;
import src.Game;
import src.RoomType;

/**
 * A simple {@link Fragment} subclass.
 */
public class BathroomFragment extends Fragment {

    private String name;
    private Game game;

    // attributes
    private ImageButton[] buttons;

    public BathroomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        buttons = new ImageButton[2];
        name = "Bathroom";
        game = Game.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_bathroom, container, false);

        buttons[BathroomItems.TOILET.ordinal()] = (ImageButton) root.findViewById(R.id.toilet);
        buttons[BathroomItems.BATH.ordinal()] = (ImageButton) root.findViewById(R.id.bath);

        game.getGameEnvironment().getHouse().getRooms()[RoomType.BATHROOM.ordinal()].getItems()[BathroomItems.TOILET.ordinal()].setId(R.id.toilet);
        game.getGameEnvironment().getHouse().getRooms()[RoomType.BATHROOM.ordinal()].getItems()[BathroomItems.BATH.ordinal()].setId(R.id.bath);

        return root;
    }

    public String getName() {
        return name;
    }

    public void addListeners(View.OnClickListener listener)
    {
        for (int i = 0; i < 2; i++)
        {
            buttons[i].setOnClickListener(listener);
        }
    }

    public boolean activateButton(int id)
    {
        for (int i = 0; i < 2; i++)
        {
            if (buttons[i].getId() == id)
            {
                buttons[i].setActivated(true);
                return true;
            }
        }
        return false;
    }

    public void deactivateAllButtons()
    {
        for (int i = 0; i < 2; i++)
        {
            buttons[i].setActivated(false);
        }
    }
}
