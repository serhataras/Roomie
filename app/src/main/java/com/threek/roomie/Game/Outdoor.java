package com.threek.roomie.Game;

import android.graphics.drawable.Drawable;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class Outdoor
{
    String name;
    Drawable background;
    boolean challengeSuccess;

    public Outdoor()
    {
        name             = "";
        background       = null;
        challengeSuccess = false;
    }

    public Outdoor(String name, Drawable background, boolean challengeSuccess){
        this.name               = name;
        this.background         = background;
        this.challengeSuccess   = challengeSuccess;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Drawable getBackground()
    {
        return background;
    }

    public void setBackground(Drawable background)
    {
        this.background = background;
    }

    public boolean isChallengeSuccess()
    {
        return challengeSuccess;
    }

    public void setChallengeSuccess(boolean challengeSuccess)
    {
        this.challengeSuccess = challengeSuccess;
    }
}