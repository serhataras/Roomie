package src;


import android.graphics.drawable.Drawable;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class Room
{
    private HouseItem[] items;
    private Drawable background;

    public static final int NUMBER_OF_ITEMS = 4;

    public Room()
    {
        items       = new HouseItem[NUMBER_OF_ITEMS];
        background  = null;
    }

    public Room(HouseItem[] items, Drawable background)
    {
        this.items      = items;
        this.background = background;
    }


    public HouseItem[] getItems()
    {
        return items;
    }

    public void setItems(HouseItem[] items)
    {
        this.items = items;
    }

    public Drawable getBackground()
    {
        return background;
    }

    public void setBackground(Drawable background)
    {
        this.background = background;
    }
}
