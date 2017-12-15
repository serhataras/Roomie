package src;

/**
 * Created by eliztekcan on 26.10.2017.
 */

import java.util.Arrays;
import src.Enums.StatType;

public class Stats
{
    // constants
    private final int MAX_STAT = 10;
    private final int MIN_STAT = 0;
    private final int NUMBER_OF_STATS = 4;

    // stat array
    private  int[] stats;

    // constructors
    public Stats(int[] stats)
    {
        //set all stats to given int array
        this.stats = stats;
    }

    public Stats()
    {
        //set all stats to maximum
        stats = new int[NUMBER_OF_STATS];
        makeStatsZero();
    }

    public int[] getStat()
    {
        return stats;
    }

    // this method adds new values to the stats
    public void updateStat(Stats stats)
    {
        if (stats != null)
        {
            int[] statsToAdd = stats.getStat();

            for (int i = 0; i < NUMBER_OF_STATS; i++)
            {
                this.stats[i] += statsToAdd[i];
                checkBoundaries(i);
            }
        }
    }

    // this method adds new value to a specific stat
    public void setStatByIndex(StatType index, int change)
    {
        stats[index.ordinal()] += change;
        checkBoundaries(index.ordinal());
    }

    public void makeStatsZero()
    {
        for(int i = 0; i < NUMBER_OF_STATS; i++)
            this.stats[i] = MIN_STAT;
    }

    public void checkBoundaries(int index)
    {
        if (stats[index] > MAX_STAT)
            stats[index] = MAX_STAT;

        if (stats[index] < MIN_STAT)
            stats[index] = MIN_STAT;
    }

    public int getStatByIndex(StatType index)
    {
        return stats[index.ordinal()];
    }

    @Override
    public String toString()
    {
        return "Stats{" +
                "stats=" + Arrays.toString(stats) +
                '}';
    }
}