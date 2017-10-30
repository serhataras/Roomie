/**
 * Created by eliztekcan on 27.10.2017.
 */
public class House
{
    Room[] rooms;

    House()
    {
        rooms = new Room[4];
    }

    House(Room[] rooms)
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