package src;

/**
 * Created by eliztekcan on 26.10.2017.
 */
import java.util.Arrays;

/**
 * Created by eliztekcan on 26.10.2017.
 */
public class Stats {
    private  int[] stats;

    private static final int MAX_STAT = 10;

    Stats(int[] stats){
        //set all stats to given int array
        this.stats = stats;
    }

    Stats(){
        //set all stats to maximum
        stats = new int[4];
        for(int i = 0; i < 4; i++)
            this.stats[i] = MAX_STAT;
    }

    public int[] getStat() {
        return stats;
    }

    public void setStat(int[] stats) {
        this.stats = stats;
    }

    public void setStatByIndex(int index, int change){
        stats[index] += change;
        checkBoundaries(index);

    }
    public void checkBoundaries(int index) {
        if (stats[index] > MAX_STAT)
            stats[index] = MAX_STAT;
        if (stats[index] < 0)
            stats[index] = 0;
    }

    public int getStatByIndex(int index){
        return stats[index];
    }

    public static int getMaxStat() {
        return MAX_STAT;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "stats=" + Arrays.toString(stats) +
                '}';
    }
}