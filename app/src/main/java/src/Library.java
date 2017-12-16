package src;

import android.graphics.drawable.Drawable;

/**
 * Created by eliztekcan on 27.10.2017.
 */

public class Library extends Outdoor
{
    /*
        0-10 db is the threshold of human hearing
        20db is as loud as leaves hitting the ground
        30db is as loud as whispering
        50db is normal conversation
        70db is heavy traffic
   */

    private final int MAX_DB = 30;
    private double currentDb;
    private boolean threshold;

    public Library()
    {
        super();
        currentDb = 0;
        threshold = false;
    }

    public int getMAX_DB() {
        return MAX_DB;
    }

    public void setCurrentDb(double currentDb) {
        this.currentDb = currentDb;
    }

    public double getCurrentDb() {
        return currentDb;
    }

    public boolean isThresholdExceeded()
    {
        if (getCurrentDb() > getMAX_DB())
        {
            threshold = true;
            return threshold;
        }
        else{
            threshold = false;
            return threshold;
        }

    }
    public void setThreshold(boolean threshold) {
        this.threshold = threshold;
    }

    @Override
    public void updateChallengeSuccess()
    {
        if (isThresholdExceeded())
        {
            super.setChallengeSuccess(false);
        }
        else
        {
            super.setChallengeSuccess(true);
        }
    }
}