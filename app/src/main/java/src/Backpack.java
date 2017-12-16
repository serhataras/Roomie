package src;
import java.util.ArrayList;

/**
 * Created by eliztekcan on 26.10.2017.
 */
public class Backpack
{
    private ArrayList<Item> itemList;

    public Backpack()
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

    public void insert(Item item){
        itemList.add(item);
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

    public boolean hasAnItem(Item item)
    {
        //checks if the given item is in the backpack
        return itemList.contains(item) == true;
    }

    public boolean isEmpty(){
        return itemList.isEmpty();
    }

    public int getItemCount(){
        return itemList.size();
    }

    public Item getItemByIndex(int i){
        return itemList.get(i);
    }

    @Override
    public String toString() {
        return "Backpack{" +
                "itemList=" + itemList +
                '}';
    }
}