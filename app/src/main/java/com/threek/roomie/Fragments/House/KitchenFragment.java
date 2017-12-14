package com.threek.roomie.Fragments.House;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.threek.roomie.R;

import src.Game;
import src.House;
import src.KitchenItems;
import src.RoomType;


public class KitchenFragment extends Fragment {

    // attributes
    private String name;
    private Game game;

    private ImageButton[] buttons;

    public KitchenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        buttons = new ImageButton[4];
        name = "Kitchen";
        game = Game.getInstance();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_kitchen, container, false);

        buttons[KitchenItems.FRIDGE.ordinal()] = (ImageButton) root.findViewById(R.id.fridge);
        buttons[KitchenItems.STALL.ordinal()] = (ImageButton) root.findViewById(R.id.stall);
        buttons[KitchenItems.CUPBOARD.ordinal()] = (ImageButton) root.findViewById(R.id.cupboard);
        buttons[KitchenItems.TABLE.ordinal()] = (ImageButton) root.findViewById(R.id.table);

        buttons[KitchenItems.FRIDGE.ordinal()].setId(House.FRIDGE_ID);
        buttons[KitchenItems.STALL.ordinal()].setId(House.STALL_ID);
        buttons[KitchenItems.CUPBOARD.ordinal()].setId(House.CUPBOARD_ID);
        buttons[KitchenItems.TABLE.ordinal()].setId(House.TABLE_ID);

        return root;
    }

    public String getName() {
        return name;
    }

    public void setButtons(ImageButton[] buttons) {
        this.buttons = buttons;
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
