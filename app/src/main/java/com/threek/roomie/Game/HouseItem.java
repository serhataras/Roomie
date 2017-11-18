package com.threek.roomie.Game;

import android.graphics.drawable.Drawable;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class HouseItem
{
    String name;
    String id;
    Drawable image;
    boolean clickable;

    HouseItem()
    {
        name        = "";
        id          = "";
        image       = null;
        clickable   = false;
    }

    HouseItem(String name, String id, Drawable image, boolean clickable){
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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