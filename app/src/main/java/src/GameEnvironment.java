package src;

import android.content.res.Resources;

import src.Enums.OptionType;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class GameEnvironment
{
    private Outdoor[] outdoors;
    private House house;

    public static final int OUTDOOR_NUMBER = 4;

    public GameEnvironment(Resources r, String pn)
    {
        outdoors = new Outdoor[OUTDOOR_NUMBER];
        outdoors[OptionType.SCHOOL_OPTION.ordinal()] = new School(r, pn);
        outdoors[OptionType.NIGHT_CLUB_OPTION.ordinal()] = new NightClub();
        outdoors[OptionType.LIBRARY_OPTION.ordinal()] = new Library();
        outdoors[OptionType.CAFE_OPTION.ordinal()] = new Cafe(r, pn);

        house = new House();
    }


    public GameEnvironment(Outdoor[] outdoor, House house)
    {
        this.house = house;
        this.outdoors = outdoor;
    }

    public Outdoor getOutdoorEnvironment(OptionType environment)
    {
        if (environment != OptionType.HOUSE_OPTION)
            return outdoors[environment.ordinal()];
        else
            return null;//Creates bugs
    }

    public void setOutdoor(Outdoor[] outdoor)
    {
        this.outdoors = outdoor;
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