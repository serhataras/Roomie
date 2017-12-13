package com.threek.roomie.Fragments.House;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.threek.roomie.R;


public class KitchenFragment extends Fragment {

    // attributes
    private static final String NAME = "Kitchen";

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

        buttons[0] = (ImageButton) root.findViewById(R.id.fridge);
        buttons[1] = (ImageButton) root.findViewById(R.id.stall);
        buttons[2] = (ImageButton) root.findViewById(R.id.item3);
        buttons[3] = (ImageButton) root.findViewById(R.id.table);

        return root;
    }

    public String getName() {
        return NAME;
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
                buttons[i].setActivated(true);
                return true;
            }
        }
        return false;
    }

    public void deactivateAllButtons()
    {
        for (int i = 0; i < 4; i++)
        {
            buttons[i].setActivated(false);
        }
    }

}
