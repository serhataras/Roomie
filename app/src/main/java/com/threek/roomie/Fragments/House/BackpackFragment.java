package com.threek.roomie.Fragments.House;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.threek.roomie.R;

import java.util.ArrayList;

import src.Item;

/**
 * A simple {@link Fragment} subclass.
 */
public class BackpackFragment extends Fragment {

    private ArrayList<Item> itemList;
    private CustomListAdapter adapter;

    public BackpackFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // take itemList from the game instance
        adapter = new CustomListAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_backpack, container, false);

        ListView listView = (ListView) root.findViewById(R.id.backpackList);
        listView.setAdapter(adapter);

        Button useAll = (Button) root.findViewById(R.id.useAll);
        useAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO use all the items
            }
        });

        Button sellAll = (Button) root.findViewById(R.id.sellAll);
        sellAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO sell all the items
            }
        });
        return root;
    }


    // custom adapter for listview
    private class CustomListAdapter extends BaseAdapter
    {
        //public constructor
        public CustomListAdapter()
        {}

        @Override
        public int getCount() {
            return itemList.size(); //returns total of items in the list
        }

        @Override
        public Object getItem(int position) {
            return itemList.get(position); //returns list item at the specified position
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent)
        {
            // inflate the layout for each list row
            convertView = LayoutInflater.from(getActivity()).inflate(R.layout.backpack_item, parent, false);

            // get current item to be displayed
            Item currentItem = (Item) getItem(position);

            // get the TextView for item name and item description
            //TextView textView = (TextView) convertView.findViewById(R.id.trip_text);
            ImageView image = (ImageView) convertView.findViewById(R.id.itemImage);
            image.setImageDrawable(currentItem.getImage());

            TextView itemText = (TextView) convertView.findViewById(R.id.itemText);
            itemText.setText(currentItem.getName());

            Button useButton = (Button) convertView.findViewById(R.id.useButton);
            useButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO use the item
                }
            });

            Button sellButton = (Button) convertView.findViewById(R.id.sellButton);
            sellButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO sell the item
                }
            });

            // returns the view for the current row
            return convertView;
        }
    }
}
