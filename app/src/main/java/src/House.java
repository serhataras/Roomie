package src;

import src.Enums.BathroomItems;
import src.Enums.BedroomItems;
import src.Enums.KitchenItems;
import src.Enums.LivingRoomItems;
import src.Enums.OptionType;
import src.Enums.RoomType;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class House
{
    private Room[] rooms;

    public static final int NUMBER_OF_ROOMS = 4;

    // bathroom buttons
    public static final int BATH_ID = idGenerator(OptionType.HOUSE_OPTION, RoomType.BATHROOM, BathroomItems.BATH);
    public static final int TOILET_ID = idGenerator(OptionType.HOUSE_OPTION, RoomType.BATHROOM, BathroomItems.TOILET);

    // bedroom buttons
    public static final int BED_ID = idGenerator(OptionType.HOUSE_OPTION, RoomType.BEDROOM, BedroomItems.BED);
    public static final int WARDROBE_ID = idGenerator(OptionType.HOUSE_OPTION, RoomType.BEDROOM, BedroomItems.WARDROBE);
    public static final int DESK_ID = idGenerator(OptionType.HOUSE_OPTION, RoomType.BEDROOM, BedroomItems.DESK);
    public static final int BOOKSHELF_ID = idGenerator(OptionType.HOUSE_OPTION, RoomType.BEDROOM, BedroomItems.BOOKSHELF);

    // kitchen buttons
    public static final int FRIDGE_ID = idGenerator(OptionType.HOUSE_OPTION, RoomType.KITCHEN, KitchenItems.FRIDGE);
    public static final int STALL_ID = idGenerator(OptionType.HOUSE_OPTION, RoomType.KITCHEN, KitchenItems.STALL);
    public static final int CUPBOARD_ID = idGenerator(OptionType.HOUSE_OPTION, RoomType.KITCHEN, KitchenItems.CUPBOARD);
    public static final int TABLE_ID = idGenerator(OptionType.HOUSE_OPTION, RoomType.KITCHEN, KitchenItems.TABLE);

    // bedroom buttons
    public static final int TV_ID = idGenerator(OptionType.HOUSE_OPTION, RoomType.LIVINGROOM, LivingRoomItems.TV);
    public static final int SOFA_ID = idGenerator(OptionType.HOUSE_OPTION, RoomType.LIVINGROOM, LivingRoomItems.SOFA);
    public static final int PLANTS_ID = idGenerator(OptionType.HOUSE_OPTION, RoomType.LIVINGROOM, LivingRoomItems.PLANTS);
    public static final int BIGTABLE_ID = idGenerator(OptionType.HOUSE_OPTION, RoomType.LIVINGROOM, LivingRoomItems.BIGTABLE);


    public House()
    {
        rooms = new Room[NUMBER_OF_ROOMS];
        for (int i = 0; i < NUMBER_OF_ROOMS; i++)
        {
            rooms[i] = new Room();
        }
        createRooms();
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
        HouseItem bath = new HouseItem("Bath", BATH_ID, null, false);
        rooms[RoomType.BATHROOM.ordinal()].getItems()[BathroomItems.BATH.ordinal()] = bath;

        HouseItem toilet = new HouseItem("Toilet", TOILET_ID, null, false);
        rooms[RoomType.BATHROOM.ordinal()].getItems()[BathroomItems.TOILET.ordinal()] = toilet;

        // bedroom
        HouseItem bed = new HouseItem("Bed", BED_ID, null, false);
        rooms[RoomType.BEDROOM.ordinal()].getItems()[BedroomItems.BED.ordinal()] = bed;

        HouseItem wardrobe = new HouseItem("Wardrobe", WARDROBE_ID, null, false);
        rooms[RoomType.BEDROOM.ordinal()].getItems()[BedroomItems.WARDROBE.ordinal()] = wardrobe;

        HouseItem desk = new HouseItem("Desk", DESK_ID, null, false);
        rooms[RoomType.BEDROOM.ordinal()].getItems()[BedroomItems.DESK.ordinal()] = desk;

        HouseItem bookshelf = new HouseItem("Bookshelf", BOOKSHELF_ID, null, false);
        rooms[RoomType.BEDROOM.ordinal()].getItems()[BedroomItems.BOOKSHELF.ordinal()] = bookshelf;

        // kitchen
        HouseItem fridge = new HouseItem("Fridge", FRIDGE_ID, null, false);
        rooms[RoomType.KITCHEN.ordinal()].getItems()[KitchenItems.FRIDGE.ordinal()] = fridge;

        HouseItem stall = new HouseItem("Stall", STALL_ID, null, false);
        rooms[RoomType.KITCHEN.ordinal()].getItems()[KitchenItems.STALL.ordinal()] = stall;

        HouseItem cupboard = new HouseItem("Cupboard", CUPBOARD_ID, null, false);
        rooms[RoomType.KITCHEN.ordinal()].getItems()[KitchenItems.CUPBOARD.ordinal()] = cupboard;

        HouseItem table = new HouseItem("Table", TABLE_ID, null, false);
        rooms[RoomType.KITCHEN.ordinal()].getItems()[KitchenItems.TABLE.ordinal()] = table;

        // living room
        HouseItem tv = new HouseItem("Tv", TV_ID, null, false);
        rooms[RoomType.LIVINGROOM.ordinal()].getItems()[LivingRoomItems.TV.ordinal()] = tv;

        HouseItem sofa = new HouseItem("Sofa", SOFA_ID, null, false);
        rooms[RoomType.LIVINGROOM.ordinal()].getItems()[LivingRoomItems.SOFA.ordinal()] = sofa;

        HouseItem plants = new HouseItem("Plants", PLANTS_ID, null, false);
        rooms[RoomType.LIVINGROOM.ordinal()].getItems()[LivingRoomItems.PLANTS.ordinal()] = plants;

        HouseItem bigtable = new HouseItem("Big table", BIGTABLE_ID, null, false);
        rooms[RoomType.LIVINGROOM.ordinal()].getItems()[LivingRoomItems.BIGTABLE.ordinal()] = bigtable;
    }

    public static int idGenerator(OptionType option, RoomType room, Enum item)
    {
        if (option == OptionType.HOUSE_OPTION)
        {
            int first = option.ordinal() * 100;
            int second = room.ordinal() * 10;
            int third = item.ordinal();
            return first + second + third;
        }
        else
        {
            return option.ordinal() * 100;
        }
    }
}