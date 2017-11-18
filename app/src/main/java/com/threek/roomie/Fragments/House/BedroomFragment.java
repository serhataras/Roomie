package com.threek.roomie.Fragments.House;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.threek.roomie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BedroomFragment extends Fragment {

    private String name;

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
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_bedroom, container, false);

        buttons[0] = (ImageButton) root.findViewById(R.id.item1);
        buttons[1] = (ImageButton) root.findViewById(R.id.item2);
        buttons[2] = (ImageButton) root.findViewById(R.id.item3);
        buttons[3] = (ImageButton) root.findViewById(R.id.item4);

        return root;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListeners(View.OnClickListener listener)
    {
        for (int i = 0; i < 4; i++)
            buttons[i].setOnClickListener(listener);
    }
}
