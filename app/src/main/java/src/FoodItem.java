package src;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class FoodItem
{
    int price;
    String name;
    int health;

    FoodItem()
    {
        price   = 0;
        name    = "";
        health  = 0;
    }

    FoodItem(String name, int health, int price)
    {
        this.price   = price;
        this.name    = name;
        this.health  = health;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", health=" + health +
                '}';
    }
}