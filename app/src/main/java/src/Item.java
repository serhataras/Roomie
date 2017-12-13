package src;
import android.graphics.drawable.Drawable;

import java.util.Arrays;

/**
 * Created by eliztekcan on 26.10.2017.
 */
public class Item {
    private String name;
    Drawable image;
    int price;
    int[] boostAmount;

    Item()
    {
        name = "";
        price = 0;
        //Health, Sociality, Grades and Money
        boostAmount = new int[4];
        image = null;
    }

    Item(String name, Drawable image, int price, int[] boostAmount)
    {
        this.name           = name;
        this.boostAmount    = boostAmount;
        this.price          = price;
        this.image          = image;

    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setImage(Drawable image)
    {
        this.image = image;
    }

    public Drawable getImage()
    {
        return image;
    }

    public void setBoostAmount(int[] boostAmount)
    {
        this.boostAmount = boostAmount;
    }

    public int[] getBoostAmount()
    {
        return boostAmount;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getPrice()
    {
        return price;
    }

    @Override
    public String toString()
    {
        return "Item{" +
                "name='" + name + '\'' +
                ", image=" + image +
                ", price=" + price +
                ", boostAmount=" + Arrays.toString(boostAmount) +
                '}';
    }

}