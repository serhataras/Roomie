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
    private static final int MIN_STAT = 0;
    public static final int NUMBER_OF_STATS = 4;

    public Stats(int[] stats){
        //set all stats to given int array
        this.stats = stats;
    }

    public Stats(){
        //set all stats to maximum
        stats = new int[NUMBER_OF_STATS];
        makeStatsZero();
    }

    public int[] getStat() {
        return stats;
    }

    // this method adds new values to stats
    public void updateStat(Stats stats)
    {
        int[] statsToAdd = stats.getStat();

        for (int i = 0; i < NUMBER_OF_STATS; i++)
        {
            this.stats[i] += statsToAdd[i];
        }
    }

    public void setStatByIndex(StatType index, int change){
        stats[index.ordinal()] += change;
        checkBoundaries(index.ordinal());
    }

    public void makeStatsZero()
    {
        for(int i = 0; i < NUMBER_OF_STATS; i++)
            this.stats[i] = MIN_STAT;
    }

    public void checkBoundaries(int index) {
        if (stats[index] > MAX_STAT)
            stats[index] = MAX_STAT;
        if (stats[index] < 0)
            stats[index] = 0;
    }

    public int getStatByIndex(StatType index){
        return stats[index.ordinal()];
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