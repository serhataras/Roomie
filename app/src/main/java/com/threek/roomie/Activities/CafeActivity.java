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
import src.FoodItem;
import src.Game;

/**
 * Created by serhat on 13.12.2017.
 */

public class CafeActivity extends AppCompatActivity  {

    private Game game;
    private TextView foodListTextView;
    private FoodItem[] menu;
    private ArrayList<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*TODO list view of the fooditems
        * TODO get the selected food item
        * TODO throw it to the house activity
        */
        game=game.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe);

        ListView lv = (ListView) findViewById(R.id.foodmenuview);
        menu=game.getFoodMenu();

        lv.setAdapter(new MyListAdaper(this, R.layout.cafe_food_item, data));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //if want something on click on the tab
            }
        });
    }

    private void generateListContent() {
        for (int i = 0; i < menu.length; i++) {
            data.add(menu[i].getName()
                    +"\nHealth: "+menu[i].getHealth()
                    +"\nPrice: "+menu[i].getPrice());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;
        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);
                viewHolder.button = (Button) convertView.findViewById(R.id.list_item_btn);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Happy Meal!" + position, Toast.LENGTH_SHORT).show();
                    //Sets the game instance of the cafe selected menu item
                    ((Cafe)game.getGameEnvironment().getOutdoorEnvironment(OptionType.CAFE_OPTION)).setSelectedFood(menu[position]);
                }
            });
            mainViewholder.title.setText(getItem(position));

            return convertView;
        }
    }
    public class ViewHolder {
        TextView title;
        Button button;
    }
}

