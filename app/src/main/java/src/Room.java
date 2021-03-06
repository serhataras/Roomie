package src;


import android.graphics.drawable.Drawable;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class Room
{
    public static final int NUMBER_OF_ITEMS = 4;

    private HouseItem[] items;
    private Drawable background;
    private String name;

    public Room()
    {
        items = new HouseItem[NUMBER_OF_ITEMS];

        for (int i = 0; i < NUMBER_OF_ITEMS; i++)
            items[i] = new HouseItem();

        background = null;
        name = "";
    }

    public Room(HouseItem[] items, Drawable background, String name)
    {
        this.items = items;
        this.background = background;
        this.name = name;
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
