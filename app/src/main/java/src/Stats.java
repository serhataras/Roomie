package src;

/**
 * Created by eliztekcan on 26.10.2017.
 */
public class Stats {
    private  int stat;
    private static final int MAX_STAT = 10;

    Stats(int stat){
        this.stat = stat;
    }

    Stats(){
        this.stat = MAX_STAT;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }
}
