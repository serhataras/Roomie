package com.threek.roomie.Game;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class NightClub extends Outdoor
{

    //TO DO
    public final int HIGHEST_SHAKE = 100;
    int shakeAmount;

    @Override
    public boolean isChallengeSuccess()
    {
        return super.isChallengeSuccess();
    }

    public int getHIGHEST_SHAKE()
    {
        return HIGHEST_SHAKE;
    }

    public int getShakeAmount()
    {
        return shakeAmount;
    }

    public void setShakeAmount(int shakeAmount)
    {
        this.shakeAmount = shakeAmount;
    }
}