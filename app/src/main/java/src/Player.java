package src;

import android.util.Log;

import src.Enums.Gender;
import src.Enums.StatType;

/**
 * Created by eliztekcan on 26.10.2017.
 */
public class Player
{
    private String name;
    private Gender gender;
    private Stats stats;
    private Backpack backpack;

    public Player()
    {
        name = "";
        gender = Gender.FEMALE;

        stats = new Stats();
        stats.makeStatsRandom();

        backpack = new Backpack();
    }

    public Player(String name, Gender gender)
    {
        this.name = name;
        this.gender = gender;
        stats = new Stats();
        backpack = new Backpack();
    }

    public void updateStats(Stats stats)
    {
        this.stats.updateStat(stats);
    }

    public void updateStatByIndex(StatType index, int change)
    {
        this.stats.setStatByIndex(index, change);
    }

    public void sellAnItem(Item item)
    {
        if (backpack.hasAnItem(item))
        {
            backpack.remove(item);
            this.stats.setStatByIndex(StatType.MONEY, item.getPrice());
        }
    }

    public void useAnItem(Item item)
    {
        if (backpack.hasAnItem(item))
        {
            int price = -1 * item.getPrice();

            // add items stats to player's stats
            updateStats(item.getBoostAmount());
            backpack.remove(item);

            // subtract price from the player's stats because using an item can't change the money
            stats.setStatByIndex(StatType.MONEY, price);
        }
    }

    public void sellAnItem(int index)
    {
        Item item = backpack.getItemByIndex(index);

        if (backpack.hasAnItem(item))
        {
            backpack.remove(index);
            Log.e("PRICE", item.toString() + "");
            this.stats.setStatByIndex(StatType.MONEY, item.getPrice());
            Log.e("MONEY", stats.getStatByIndex(StatType.MONEY) + "");
        }
    }

    public void useAnItem(int index)
    {
        Item item = backpack.getItemByIndex(index);
        if (backpack.hasAnItem(item))
        {
            int price = -1 * item.getPrice();

            // add items stats to player's stats
            updateStats(item.getBoostAmount());
            backpack.remove(index);

            // subtract price from the player's stats because using an item can't change the money
            stats.setStatByIndex(StatType.MONEY, price);
        }
    }

    public void sellAllItems()
    {
        int i = backpack.getItemCount();
        while (i > 0)
        {
            i--;
            sellAnItem(backpack.getItemByIndex(0));
        }
    }

    public void useAllItems(){
        int i = backpack.getItemCount();
        while (i > 0)
        {
            i--;
            useAnItem(backpack.getItemByIndex(0));
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", stats=" + stats +
                ", backpack=" + backpack +
                '}' + "\n";
    }

    //test
    public static void main(String[] args){
        Player p = new Player();
        /*ItemCollection i = new ItemCollection();
        Randomizer r = new Randomizer();
        for(int k = 0; k < 5; k++)
            p.backpack.insert(r.throwItem());
        System.out.print(p);
        p.sellAllItems();
        System.out.print(p);*/
        int[] s = {-11, -2, 5, 1000};
        Stats stats = new Stats(s);
        p.updateStats(stats);
        System.out.print(p);
    }
}