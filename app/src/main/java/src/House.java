package src;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class House
{
    private Room[] rooms;

    public static final int NUMBER_OF_ROOMS = 4;

    public House()
    {
        rooms = new Room[NUMBER_OF_ROOMS];
    }

    public House(Room[] rooms)
    {
        this.rooms = rooms;
    }

    public Room[] getRooms()
    {
        return rooms;
    }

    public void setRooms(Room[] rooms)
    {
        this.rooms = rooms;
    }
}