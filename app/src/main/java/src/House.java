package src;

import com.threek.roomie.R;

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

    private void createRooms()
    {
        // bathroom
        HouseItem bath = new HouseItem("Bath", 0, null, false);
        rooms[RoomType.BATHROOM.ordinal()].getItems()[BathroomItems.BATH.ordinal()] = bath;

        HouseItem toilet = new HouseItem("Toilet", 0, null, false);
        rooms[RoomType.BATHROOM.ordinal()].getItems()[BathroomItems.TOILET.ordinal()] = toilet;

        // bedroom
        HouseItem bed = new HouseItem("Bed", 0, null, false);
        rooms[RoomType.BEDROOM.ordinal()].getItems()[BedroomItems.BED.ordinal()] = bed;

        HouseItem wardrobe = new HouseItem("Wardrobe", 0, null, false);
        rooms[RoomType.BEDROOM.ordinal()].getItems()[BedroomItems.WARDROBE.ordinal()] = wardrobe;

        HouseItem desk = new HouseItem("Desk", 0, null, false);
        rooms[RoomType.BEDROOM.ordinal()].getItems()[BedroomItems.DESK.ordinal()] = desk;

        HouseItem bookshelf = new HouseItem("Bookshelf", 0, null, false);
        rooms[RoomType.BEDROOM.ordinal()].getItems()[BedroomItems.BOOKSHELF.ordinal()] = bookshelf;

        // kitchen
        HouseItem fridge = new HouseItem("Fridge", 0, null, false);
        rooms[RoomType.KITCHEN.ordinal()].getItems()[KitchenItems.FRIDGE.ordinal()] = fridge;

        HouseItem stall = new HouseItem("Stall", 0, null, false);
        rooms[RoomType.KITCHEN.ordinal()].getItems()[KitchenItems.STALL.ordinal()] = stall;

        HouseItem cupboard = new HouseItem("Cupboard", 0, null, false);
        rooms[RoomType.KITCHEN.ordinal()].getItems()[KitchenItems.CUPBOARD.ordinal()] = cupboard;

        HouseItem table = new HouseItem("Table", 0, null, false);
        rooms[RoomType.KITCHEN.ordinal()].getItems()[KitchenItems.TABLE.ordinal()] = table;

        // living room
        HouseItem tv = new HouseItem("Tv", 0, null, false);
        rooms[RoomType.LIVINGROOM.ordinal()].getItems()[LivingRoomItems.TV.ordinal()] = tv;

        HouseItem sofa = new HouseItem("Sofa", 0, null, false);
        rooms[RoomType.LIVINGROOM.ordinal()].getItems()[LivingRoomItems.SOFA.ordinal()] = sofa;

        HouseItem plants = new HouseItem("Plants", 0, null, false);
        rooms[RoomType.LIVINGROOM.ordinal()].getItems()[LivingRoomItems.PLANTS.ordinal()] = plants;

        HouseItem bigtable = new HouseItem("Big table", 0, null, false);
        rooms[RoomType.LIVINGROOM.ordinal()].getItems()[LivingRoomItems.BIGTABLE.ordinal()] = bigtable;
    }
}