package com.threek.roomie.Game;

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

    public final int HIGHEST_DB = 30;
    int currentDb;

    @Override
    public boolean isChallengeSuccess() {
        return super.isChallengeSuccess();
    }

    public int getHIGHEST_DB() {
        return HIGHEST_DB;
    }

    public void setCurrentDb(int currentDb) {
        this.currentDb = currentDb;
    }

    public int getCurrentDb() {
        return currentDb;
    }
}