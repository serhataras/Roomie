package com.threek.roomie.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.threek.roomie.R;

import java.util.ArrayList;
import java.util.List;

import src.*;
import src.Enums.OptionType;
import src.Enums.StatType;
import src.FoodItem;
import src.Game;

/**
 * Created by serhat on 13.12.2017.
 */

public class CafeActivity extends AppCompatActivity
{
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        game = Game.getInstance();
        CustomListAdapter adapter = new CustomListAdapter();

        ListView listView = (ListView) findViewById(R.id.foodmenuview);
        listView.setAdapter(adapter);
    }

    // custom adapter for listview
    private class CustomListAdapter extends BaseAdapter
    {
        //public constructor
        public CustomListAdapter()
        {}

        @Override
        public int getCount() {
            return game.getFoodMenu().length; //returns total of items in the list
        }

        @Override
        public Object getItem(int position) {
            return game.getFoodMenu()[position]; //returns list item at the specified position
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent)
        {
            // inflate the layout for each list row
            convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.cafe_food_item, parent, false);

            // get current item to be displayed
            final FoodItem currentItem = (FoodItem) getItem(position);

            // get the TextView for item name and item description
            TextView itemText = (TextView) convertView.findViewById(R.id.list_item_text);
            itemText.setText(currentItem.getName());

            TextView priceText = (TextView) convertView.findViewById(R.id.foodPrice);
            priceText.setText("Price: " + currentItem.getPrice());

            Button useButton = (Button) convertView.findViewById(R.id.list_item_btn);
            useButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Cafe)game.getGameEnvironment().getOutdoorEnvironment(OptionType.CAFE_OPTION)).setSelectedFood((FoodItem)getItem(position));
                    finish();
                }
            });

            // deactivate button if the player has not enough money
            if (game.getPlayer().getStats().getStatByIndex(StatType.MONEY) < currentItem.getPrice())
            {
                useButton.setEnabled(false);
                useButton.setAlpha(0.7f);
                itemText.setText(currentItem.getName() + " (Not enough money to buy!)");
            }

            // returns the view for the current row
            return convertView;
        }
    }
}