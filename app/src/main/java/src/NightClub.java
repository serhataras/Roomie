package src;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class NightClub extends Outdoor
{

    //TO DO
    private final int HIGHEST_SHAKE = 100;
    private int shakeAmount;

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