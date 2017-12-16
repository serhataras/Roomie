package src;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import src.Enums.BathroomItems;
import src.Enums.BedroomItems;
import src.Enums.KitchenItems;
import src.Enums.LivingRoomItems;
import src.Enums.OptionType;
import src.Enums.RoomType;
import src.Enums.StatType;

/**
 * Created by eliztekcan on 30.10.2017.
 */

public class Events
{
    private Graph eventCollection;
    private Event start;
    private Event[] allEvents;
    private static final int MAX_EVENT_COUNT = 7;


    Events(Resources r, String pn){
        allEvents = setEvents(r, pn);
        start = allEvents[0];
        eventCollection = new Graph(MAX_EVENT_COUNT);
        connectEvents(r, pn);
        //Log.e("START!!!!", start.toString());
    }

    public Event[] setEvents(Resources r, String pn)
    {
        BufferedReader br = null;
        InputStream in = null;

        try {
            in = r.openRawResource(r.getIdentifier("events", "raw", pn));
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String sCurrentLine;
            allEvents = new Event[MAX_EVENT_COUNT];
            int index = 0;
            while ((sCurrentLine = br.readLine()) != null && index < MAX_EVENT_COUNT) {
                int starInd = sCurrentLine.indexOf('*');
                int lineInd = sCurrentLine.indexOf('-');
                int qInd    = sCurrentLine.indexOf('?');
                int aInd    = sCurrentLine.indexOf('+');
                int eqInd   = sCurrentLine.indexOf('=');
                int colInd  = sCurrentLine.indexOf(':');
                int lInd    = sCurrentLine.indexOf('~');
                int slashInd    = sCurrentLine.indexOf('@');
                int backslashInd = sCurrentLine.indexOf('<');

                Option opt1 = null, opt2 = null;
                Stats effect = new Stats();
                effect.setStatByIndex(StatType.HEALTH, Integer.parseInt(sCurrentLine.substring(eqInd+1, eqInd+3).replaceAll("\\s+","")));
                effect.setStatByIndex(StatType.SOCIALITY, Integer.parseInt(sCurrentLine.substring(eqInd+4, eqInd+6).replaceAll("\\s+","")));
                effect.setStatByIndex(StatType.GRADES, Integer.parseInt(sCurrentLine.substring(eqInd+7, eqInd+9).replaceAll("\\s+","")));
                effect.setStatByIndex(StatType.MONEY, Integer.parseInt(sCurrentLine.substring(eqInd+10, eqInd+12).replaceAll("\\s+","")));
                if(sCurrentLine.substring(qInd+2, qInd+3).equals("H") ) //house
                {
                    RoomType rtype = null;
                    System.out.println(sCurrentLine.substring(slashInd+1, slashInd+2));
                    int id = 0;
                    if(sCurrentLine.substring(slashInd+1, slashInd+2).equals("L"))
                        rtype = RoomType.LIVINGROOM;
                    else if(sCurrentLine.substring(slashInd+1, slashInd+2).equals("K"))
                        rtype = RoomType.KITCHEN;
                    else if(sCurrentLine.substring(slashInd+1, slashInd+2).equals("E"))
                        rtype = RoomType.BEDROOM;
                    else if(sCurrentLine.substring(slashInd+1, slashInd+2).equals("A"))
                        rtype = RoomType.BATHROOM;

                    System.out.println("2" + rtype);
                    if(rtype == RoomType.LIVINGROOM)
                    {
                        LivingRoomItems lr = null;
                        if(sCurrentLine.substring(slashInd+3, slashInd+4).equals('T')) //tv
                        {
                            lr = LivingRoomItems.TV;
                        }
                        else if(sCurrentLine.substring(slashInd+3, slashInd+4).equals("S")) //sofa
                        {
                            lr = LivingRoomItems.SOFA;
                        }
                        else if(sCurrentLine.substring(slashInd+3, slashInd+4).equals("P")) //plants
                        {
                            lr = LivingRoomItems.PLANTS;
                        }
                        else if(sCurrentLine.substring(slashInd+3, slashInd+4).equals("B")) //bigtable
                        {
                            lr = LivingRoomItems.BIGTABLE;
                        }
                        System.out.println("1 " + lr);
                        id = House.idGenerator(OptionType.HOUSE_OPTION, RoomType.LIVINGROOM, lr);
                    }

                    else if(rtype == RoomType.KITCHEN)
                    {
                        KitchenItems k = null;
                        if(sCurrentLine.substring(slashInd+3, slashInd+4).equals("F")) //fridge
                        {
                            k = KitchenItems.FRIDGE;
                        }
                        else if(sCurrentLine.substring(slashInd+3, slashInd+4).equals("C")) //cupboard
                        {
                            k = KitchenItems.CUPBOARD;
                        }
                        else if(sCurrentLine.substring(slashInd+3, slashInd+4).equals("S")) //stall
                        {
                            k = KitchenItems.STALL;
                        }
                        else if(sCurrentLine.substring(slashInd+3, slashInd+5).equals("T")) //table
                        {
                            k = KitchenItems.TABLE;
                        }
                        System.out.println("Here:!!!!!" + k);
                        id = House.idGenerator(OptionType.HOUSE_OPTION, RoomType.KITCHEN, k);
                    }

                    else if(rtype == RoomType.BEDROOM)
                    {
                        BedroomItems bedr = null;
                        if(sCurrentLine.substring(slashInd+3, slashInd+4).equals("E")) //bed
                        {
                            bedr = BedroomItems.BED;
                        }
                        else if(sCurrentLine.substring(slashInd+3, slashInd+4).equals("O")) //bookshelf
                        {
                            bedr = BedroomItems.BOOKSHELF;
                        }
                        else if(sCurrentLine.substring(slashInd+3, slashInd+4).equals("D")) //desk
                        {
                            bedr = BedroomItems.DESK;
                        }
                        else if(sCurrentLine.substring(slashInd+3, slashInd+4).equals("W")) //wardrobe
                        {
                            bedr = BedroomItems.WARDROBE;
                        }
                        id = House.idGenerator(OptionType.HOUSE_OPTION, RoomType.BEDROOM, bedr);
                    }

                    else if(rtype == RoomType.BATHROOM)
                    {
                        BathroomItems bathr = null;
                        if(sCurrentLine.substring(slashInd+3, slashInd+4).equals("B")) //bath
                        {
                            bathr = BathroomItems.BATH;
                        }
                        else if(sCurrentLine.substring(slashInd+3, slashInd+4).equals("T")) //toilet
                        {
                            bathr = BathroomItems.TOILET;
                        }
                        id = House.idGenerator(OptionType.HOUSE_OPTION, RoomType.BATHROOM, bathr);
                    }

                    opt1 = new Option(OptionType.HOUSE_OPTION, sCurrentLine.substring(starInd, lineInd), effect, id);

                }
                else if(sCurrentLine.substring(qInd+2, qInd+3).equals("S") ) //school
                {
                    int id = House.idGenerator(OptionType.SCHOOL_OPTION,null,null);
                    opt1 = new Option(OptionType.SCHOOL_OPTION, sCurrentLine.substring(starInd, lineInd), effect, id);
                }
                else if(sCurrentLine.substring(qInd+2, qInd+3).equals("L") ) //library
                {
                    int id = House.idGenerator(OptionType.LIBRARY_OPTION,null,null);
                    opt1 = new Option(OptionType.LIBRARY_OPTION, sCurrentLine.substring(starInd, lineInd), effect, id);
                }
                else if(sCurrentLine.substring(qInd+2, qInd+3).equals("N") ) //night club
                {
                    int id = House.idGenerator(OptionType.NIGHT_CLUB_OPTION,null,null);
                    opt1 = new Option(OptionType.NIGHT_CLUB_OPTION, sCurrentLine.substring(starInd, lineInd), effect,id);
                }
                else if(sCurrentLine.substring(qInd+2, qInd+3).equals("C") ) //cafe
                {
                    int id = House.idGenerator(OptionType.CAFE_OPTION,null,null);
                    opt1 = new Option(OptionType.CAFE_OPTION, sCurrentLine.substring(starInd, lineInd), effect, id);
                }


                effect.makeStatsZero();
                effect = new Stats();

                effect.setStatByIndex(StatType.HEALTH, Integer.parseInt(sCurrentLine.substring(colInd+1, colInd+3).replaceAll("\\s+","")));
                effect.setStatByIndex(StatType.SOCIALITY, Integer.parseInt(sCurrentLine.substring(colInd+4, colInd+6).replaceAll("\\s+","")));
                effect.setStatByIndex(StatType.GRADES, Integer.parseInt(sCurrentLine.substring(colInd+7, colInd+9).replaceAll("\\s+","")));
                effect.setStatByIndex(StatType.MONEY, Integer.parseInt(sCurrentLine.substring(colInd+10, colInd+12).replaceAll("\\s+","")));

                //option 2
                if(sCurrentLine.substring(qInd+3, qInd+4).equals("H"))
                {
                    RoomType rtype = null;
                    int id = 0;

                    if(sCurrentLine.substring(backslashInd+1, backslashInd+2).equals("L"))
                        rtype = RoomType.LIVINGROOM;
                    else if(sCurrentLine.substring(backslashInd+1, backslashInd+2).equals("K"))
                        rtype = RoomType.KITCHEN;
                    else if(sCurrentLine.substring(backslashInd+1, backslashInd+2).equals("E"))
                        rtype = RoomType.BEDROOM;
                    else if(sCurrentLine.substring(backslashInd+1, backslashInd+2).equals("A"))
                        rtype = RoomType.BATHROOM;


                    if(rtype == RoomType.LIVINGROOM)
                    {
                        LivingRoomItems lr = null;
                        if(sCurrentLine.substring(backslashInd+3, backslashInd+4).equals("T")) //tv
                        {
                            lr = LivingRoomItems.TV;
                        }
                        else if(sCurrentLine.substring(backslashInd+3, backslashInd+4).equals("S")) //sofa
                        {
                            lr = LivingRoomItems.SOFA;
                        }
                        else if(sCurrentLine.substring(backslashInd+3, backslashInd+4).equals("P")) //plants
                        {
                            lr = LivingRoomItems.PLANTS;
                        }
                        else if(sCurrentLine.substring(backslashInd+3, backslashInd+4).equals("B")) //bigtable
                        {
                            lr = LivingRoomItems.BIGTABLE;
                        }
                        id = House.idGenerator(OptionType.HOUSE_OPTION, RoomType.LIVINGROOM, lr);
                    }

                    if(rtype == RoomType.KITCHEN)
                    {
                        KitchenItems k = null;
                        if(sCurrentLine.substring(backslashInd+3, backslashInd+4).equals("F")) //fridge
                        {
                            k = KitchenItems.FRIDGE;
                        }
                        else if(sCurrentLine.substring(backslashInd+3, backslashInd+4).equals("C")) //cupboard
                        {
                            k = KitchenItems.CUPBOARD;
                        }
                        else if(sCurrentLine.substring(backslashInd+3, backslashInd+4).equals("S")) //stall
                        {
                            k = KitchenItems.STALL;
                        }
                        else if(sCurrentLine.substring(backslashInd+3, backslashInd+4).equals("T")) //table
                        {
                            k = KitchenItems.TABLE;
                        }
                        id = House.idGenerator(OptionType.HOUSE_OPTION, RoomType.KITCHEN, k);
                    }

                    if(rtype == RoomType.BEDROOM)
                    {
                        BedroomItems bedr = null;
                        if(sCurrentLine.substring(backslashInd+3, backslashInd+4).equals("E")) //bed
                        {
                            bedr = BedroomItems.BED;
                        }
                        else if(sCurrentLine.substring(backslashInd+3, backslashInd+4).equals("O")) //bookshelf
                        {
                            bedr = BedroomItems.BOOKSHELF;
                        }
                        else if(sCurrentLine.substring(backslashInd+3, backslashInd+4).equals("D")) //desk
                        {
                            bedr = BedroomItems.DESK;
                        }
                        else if(sCurrentLine.substring(backslashInd+3, backslashInd+4).equals("W")) //wardrobe
                        {
                            bedr = BedroomItems.WARDROBE;
                        }
                        id = House.idGenerator(OptionType.HOUSE_OPTION, RoomType.BEDROOM, bedr);
                    }

                    if(rtype == RoomType.BATHROOM)
                    {
                        BathroomItems bathr = null;
                        if(sCurrentLine.substring(backslashInd+3, backslashInd+4).equals("B")) //bath
                        {
                            bathr = BathroomItems.BATH;
                        }
                        else if(sCurrentLine.substring(backslashInd+3, backslashInd+4).equals("T")) //toilet
                        {
                            bathr = BathroomItems.TOILET;
                        }
                        id = House.idGenerator(OptionType.HOUSE_OPTION, RoomType.BATHROOM, bathr);
                    }


                    opt2 = new Option(OptionType.HOUSE_OPTION, sCurrentLine.substring(lineInd, qInd), effect,id);

                }
                else if(sCurrentLine.substring(qInd+3, qInd+4).equals("S") )
                {
                    int id = House.idGenerator(OptionType.SCHOOL_OPTION,null,null);
                    opt2 = new Option(OptionType.SCHOOL_OPTION, sCurrentLine.substring(lineInd, qInd), effect, id);

                }
                else if(sCurrentLine.substring(qInd+3, qInd+4).equals("L") )
                {
                    int id = House.idGenerator(OptionType.LIBRARY_OPTION,null,null);
                    opt2 = new Option(OptionType.LIBRARY_OPTION, sCurrentLine.substring(lineInd, qInd), effect, id);

                }
                else if(sCurrentLine.substring(qInd+3, qInd+4).equals("N") )
                {
                    int id = House.idGenerator(OptionType.NIGHT_CLUB_OPTION,null,null);
                    opt2 = new Option(OptionType.NIGHT_CLUB_OPTION, sCurrentLine.substring(lineInd, qInd), effect, id);

                }
                else if(sCurrentLine.substring(qInd+3, qInd+4).equals("C") )
                {
                    int id = House.idGenerator(OptionType.NIGHT_CLUB_OPTION,null,null);
                    opt2 = new Option(OptionType.CAFE_OPTION, sCurrentLine.substring(lineInd, qInd), effect, id);
                }
                Option[] arr = new Option[Event.getMaxOption()];
                arr[0] = opt1;
                arr[1] = opt2;
                allEvents[index] = new Event(sCurrentLine.substring(0, starInd),arr);
                if(index == 0)
                    start = allEvents[0];
                index++;

            }
            try {

                if (br != null)
                    br.close();
                if( in != null)
                    in.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allEvents;
    }
    public Graph getEventCollection() {
        return eventCollection;
    }

    public void setEventCollection(Graph eventCollection) {
        this.eventCollection = eventCollection;
    }

    public Event getStart() {
        return start;
    }

    public Event getLeft(Event event){
        List<Event> list = eventCollection.getNeighbors(event);
        if(!list.isEmpty())
            return list.get(0);

        return null;
    }

    public Event getRight(Event event){
        List<Event> list = eventCollection.getNeighbors(event);
        if(!list.isEmpty())
            return list.get(1);

        return null;
    }

    public void connectEvents(Resources r, String pn){
        BufferedReader br = null;
        InputStream in = null;

        try {
            in = r.openRawResource(r.getIdentifier("events", "raw", pn));
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String sCurrentLine;
            int index = 0;
            while ((sCurrentLine = br.readLine()) != null && index < MAX_EVENT_COUNT) {
                int lInd    = sCurrentLine.indexOf('~');
                List<Event> neighbors =  new LinkedList<Event>();
                Event cur   = allEvents[index];
                int left    = Integer.parseInt(sCurrentLine.substring(lInd+1, lInd+4).replaceAll("\\s+",""))-1;
                int right   = Integer.parseInt(sCurrentLine.substring(lInd+3, lInd+5).replaceAll("\\s+",""))-1;
                if(left != -1)
                    neighbors.add(allEvents[left]);
                if ( right != -1)
                neighbors.add(allEvents[right]);
                eventCollection.setNeighbors(cur, neighbors);
                index ++;
            }
            try {

                if (br != null)
                    br.close();

                if (in != null)
                    in.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Events{" +
                "eventCollection=" + eventCollection +
                ", allEvents=" + Arrays.toString(allEvents) +
                '}';
    }

    //test
    /*public static void main(String[] args){
        Events e = new Events();
        //System.out.println(e.start);
        System.out.println(e.eventCollection.getNeighbors(e.start));
        System.out.println(e);
        System.out.println(e.eventCollection);
    }*/

}