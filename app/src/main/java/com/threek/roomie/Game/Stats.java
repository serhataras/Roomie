package com.threek.roomie.Game;

/**
 * Created by eliztekcan on 26.10.2017.
 */
public class Stats {
    int stat;
    public static final int MAX_STAT = 10;

    Stats(int stat){
        this.stat = stat;
    }

    public Stats(){
        this.stat = MAX_STAT;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }
}
