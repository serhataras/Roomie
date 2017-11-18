package com.threek.roomie.Game;

import java.util.ArrayList;

/**
 * Created by eliztekcan on 26.10.2017.
 */
public class Backpack {
    ArrayList<Item> itemList;

    public Backpack(){
        itemList = new ArrayList<Item>();
    }

    public boolean remove(Item item){
        return itemList.remove(item);
    }

    public Item remove(int index){
        return itemList.remove(index);
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }
}
