package src;
/**
 * Created by eliztekcan on 27.10.2017.
 */
public class GameEnvironment
{
    private Outdoor[] outdoor;
    private House house;

    public static final int OUTDOOR_NUMBER = 4;

    public GameEnvironment()
    {
        outdoor     = new Outdoor[OUTDOOR_NUMBER];
        house       = new House();
    }


    public GameEnvironment(Outdoor[] outdoor, House house)
    {
        this.house      = house;
        this.outdoor    = outdoor;
    }

    public Outdoor getOutdoorEnvironment(OutdoorEnvironment environment)
    {
        return outdoor[environment.ordinal()];
    }

    public void setOutdoor(Outdoor[] outdoor)
    {
        this.outdoor = outdoor;
    }

    public House getHouse()
    {
        return house;
    }

    public void setHouse(House house)
    {
        this.house = house;
    }
}