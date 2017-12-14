package com.threek.roomie.Fragments.House;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.threek.roomie.R;

import src.BedroomItems;
import src.Game;
import src.House;
import src.RoomType;

/**
 * A simple {@link Fragment} subclass.
 */
public class BedroomFragment extends Fragment {

    private String name;
    private Game game;

    // attributes
    private ImageButton[] buttons;

    public BedroomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        buttons = new ImageButton[4];
        name = "Bedroom";
        game = Game.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_bedroom, container, false);

        buttons[BedroomItems.BED.ordinal()] = (ImageButton) root.findViewById(R.id.bed);
        buttons[BedroomItems.WARDROBE.ordinal()] = (ImageButton) root.findViewById(R.id.wardrobe);
        buttons[BedroomItems.DESK.ordinal()] = (ImageButton) root.findViewById(R.id.desk);
        buttons[BedroomItems.BOOKSHELF.ordinal()] = (ImageButton) root.findViewById(R.id.bookshelf);

        buttons[BedroomItems.BED.ordinal()].setId(House.BED_ID);
        buttons[BedroomItems.WARDROBE.ordinal()].setId(House.WARDROBE_ID);
        buttons[BedroomItems.DESK.ordinal()].setId(House.DESK_ID);
        buttons[BedroomItems.BOOKSHELF.ordinal()].setId(House.BOOKSHELF_ID);


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
