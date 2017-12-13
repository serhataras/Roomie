package src;

import java.util.ArrayList;

/**
 * Created by eliztekcan on 26.10.2017.
 */
public class Backpack {
    private ArrayList<Item> itemList;

    Backpack()
    {
        itemList = new ArrayList<Item>();
    }

    public boolean remove(Item item)
    {
        return itemList.remove(item);
    }

    public Item remove(int index)
    {
        return itemList.remove(index);
    }

    public ArrayList<Item> getItemList()
    {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList)
    {
        this.itemList = itemList;
    }

    public void addItem(Item item)
    {
        itemList.add(item);
    }

    public boolean hasAnItem(Item item){
        //checks if the given item is in the backpack
       return itemList.contains(item) == true;
    }
}
