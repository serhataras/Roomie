package src;

import android.graphics.drawable.Drawable;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class Outdoor
{
    private String name;
    private Drawable background;
    private boolean challengeSuccess;
    Outdoor()
    {
        name             = "";
        background       = null;
        challengeSuccess = false;
    }

    Outdoor(String name, Drawable background){
        this.name               = name;
        this.background         = background;
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

    public void setBackground(Drawable background) {
        this.background = background;
    }

    public boolean isChallengeSuccess() {
        return challengeSuccess;
    }

    public void setChallengeSuccess(boolean challengeSuccess) {
        this.challengeSuccess = challengeSuccess;
    }
}