package src;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class GameEnvironment
{
    private Outdoor[] outdoor;
    private House house;

    GameEnvironment()
    {
        outdoor     = new Outdoor[4];
        house       = new House();
    }


    GameEnvironment(Outdoor[] outdoor, House house)
    {
        this.house      = house;
        this.outdoor    = outdoor;
    }

    public Outdoor[] getOutdoor()
    {
        return outdoor;
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