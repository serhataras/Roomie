package src;
import android.graphics.drawable.Drawable;

import java.util.Arrays;

/**
 * Created by eliztekcan on 26.10.2017.
 */
public class Item {
    private String name;
    private Drawable image;
    private Stats boostAmount;
    private int price;

    public Item()
    {
        name = "";
        image = null;
        boostAmount = new Stats();
        price = 0;
    }

    public Item(String name, Drawable image, Stats boostAmount)
    {
        this.name           = name;
        this.boostAmount    = boostAmount;
        this.price          = boostAmount.getStatByIndex(StatType.MONEY);
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

    public void setBoostAmount(Stats boostAmount)
    {
        this.boostAmount = boostAmount;
    }

    public Stats getBoostAmount()
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
                ", boostAmount=" + boostAmount.toString() +
                '}';
    }

}