package src;


import android.graphics.drawable.Drawable;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class Room
{
    private HouseItem[] items;
    private Drawable background;

    Room()
    {
        items       = new HouseItem[3];
        background  = null;
    }

    Room(HouseItem[] items, Drawable background)
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
