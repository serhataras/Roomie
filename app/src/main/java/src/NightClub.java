package src;

/**
 * Created by eliztekcan on 27.10.2017.
 */

public class NightClub extends Outdoor
{
    //TO DO
    private final int MAX_SHAKE = 100;
    private int shakeAmount;

    public NightClub()
    {
        super();
        shakeAmount = 0;
    }

    public int getMAX_SHAKE()
    {
        return MAX_SHAKE;
    }

    public int getShakeAmount()
    {
        return shakeAmount;
    }

    public void setShakeAmount(int shakeAmount)
    {
        this.shakeAmount = shakeAmount;

    }

    @Override
    public void updateChallengeSuccess()
    {
       if(getShakeAmount() > getMAX_SHAKE())
       {
           super.setChallengeSuccess(true);
       }
       else
       {
           super.setChallengeSuccess(false);
       }

    }
}