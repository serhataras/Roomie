package src;

import android.graphics.drawable.Drawable;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class HouseItem
{
    private String name;
    private int id;
    private Drawable image;
    private boolean clickable;

    public HouseItem()
    {
        name        = "";
        id          = -1;
        image       = null;
        clickable   = false;
    }

    public HouseItem(String name, int id, Drawable image, boolean clickable)
    {
        this.name       = name;
        this.id         = id;
        this.image      = image;
        this.clickable  = clickable;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }
}