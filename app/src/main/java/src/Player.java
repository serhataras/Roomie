package src;

/**
 * Created by eliztekcan on 26.10.2017.
 */
public class Player {
    private String name;
    private Gender gender;
    private Stats stats;
    private Backpack backpack;

    Player(){
        name = "";
        gender = Gender.FEMALE;
        stats = new Stats();
        backpack = new Backpack();
    }

    Player(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        stats = new Stats();
        backpack = new Backpack();
    }

    public void updateStats(int[] stats){
        for(int i = 0; i < 4; i++){
            this.stats.setStatByIndex(i,stats[i]);
        }
    }


    public void sellAnItem(Item item){
        if (backpack.hasAnItem(item))
        {
            backpack.remove(item);
            this.stats.setStatByIndex(3, item.getPrice());
        }

    }

    public void useAnItem(Item item){
        if (backpack.hasAnItem(item))
        {
            backpack.remove(item);
            for(int i = 0; i < 4; i++){
                stats.setStatByIndex(i, item.getBoostAmount()[i]);
            }
        }
    }

    public void sellAllItems()
    {
        int i = backpack.getItemCount();
        while(i > 0){
            i--;
            sellAnItem(backpack.getItemByIndex(0));
        }
    }

    public void useAllItems(){
        int i = backpack.getItemCount();
        while(i > 0){
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
        p.updateStats(s);
        System.out.print(p);
    }
}