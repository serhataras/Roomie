package src;

/**
 * Created by eliztekcan on 26.10.2017.
 */
public class Player {
    private String name;
    private Gender gender;
    private Stats[] stats;
    private Backpack backpack;

    Player(){
        name = "";
        gender = Gender.FEMALE;
        stats = new Stats[4];
        for(int i = 0; i < 4; i++){
            stats[i] = new Stats();
        }
        backpack = new Backpack();
    }

    Player(String name, Gender gender){
        this.name = name;
        this.gender = gender;
        stats = new Stats[4];
        for(int i = 0; i < 4; i++){
            stats[i] = new Stats();
        }
        backpack = new Backpack();
    }

    //***********************
    public void makeChoice(){
        //TO DO
    }
    //************************

    public void setStat(int change, StatType type) {
        if(type == StatType.HEALTH){
            stats[0].setStat(stats[0].getStat() + change);
        }
        else if(type == StatType.SOCIALITY){
            stats[1].setStat(stats[1].getStat() + change);
        }
        else if (type == StatType.GRADES){
            stats[2].setStat(stats[2].getStat() + change);
        }
        else {
            stats[3].setStat(stats[3].getStat() + change);
        }
    }

    public void sell(Item item){

        if(stats[3].getStat() < Stats.MAX_STAT)
        {
            backpack.remove(item);
            //stats[3] stores current money
            stats[3].setStat(stats[3].getStat() + item.getPrice());
        }
    }

    public void useItem(Item item){

        if( stats[0].getStat() < Stats.MAX_STAT){
            stats[0].setStat(stats[0].getStat() + item.getBoostAmount()[0]);
        }

        if(stats[1].getStat() < Stats.MAX_STAT) {
            stats[1].setStat(stats[1].getStat() + item.getBoostAmount()[1]);
        }

        if(stats[2].getStat() < Stats.MAX_STAT) {
            stats[2].setStat(stats[2].getStat() + item.getBoostAmount()[2]);
        }

        if(stats[3].getStat() < Stats.MAX_STAT) {
            stats[3].setStat(stats[3].getStat() + item.getBoostAmount()[3]);
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

    public Stats[] getStats() {
        return stats;
    }

    public void setStats(Stats[] stats) {
        this.stats = stats;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public Backpack getBackpack() {
        return backpack;
    }
}
