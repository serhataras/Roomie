package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by eliztekcan on 30.10.2017.
 */

public class Events
{
    private Graph eventCollection;
    private Event start;
    private Event[] allEvents;
    private static final int MAX_EVENT_COUNT = 6;
    private static final String FILE_NAME = "/Users/eliztekcan/Desktop/RoomieLogic/src/Other/Events.txt";


    Events(){
        allEvents = setEvents();
        start = allEvents[0];
        eventCollection = new Graph(MAX_EVENT_COUNT);
        connectEvents();
    }

    private Event[] setEvents()
    {
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(FILE_NAME);
            br = new BufferedReader(fr);

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
                Option opt1 = null, opt2 = null;
                Stats effect = new Stats();
                effect.setStatByIndex(StatType.HEALTH, Integer.parseInt(sCurrentLine.substring(eqInd+1, eqInd+3).replaceAll("\\s+","")));
                effect.setStatByIndex(StatType.SOCIALITY, Integer.parseInt(sCurrentLine.substring(eqInd+4, eqInd+6).replaceAll("\\s+","")));
                effect.setStatByIndex(StatType.GRADES, Integer.parseInt(sCurrentLine.substring(eqInd+7, eqInd+9).replaceAll("\\s+","")));
                effect.setStatByIndex(StatType.MONEY, Integer.parseInt(sCurrentLine.substring(eqInd+10, eqInd+12).replaceAll("\\s+","")));
                if(sCurrentLine.substring(qInd+2, qInd+3).equals("H") )
                {
                    opt1 = new Option(OptionType.HOUSE_OPTION, sCurrentLine.substring(starInd, lineInd), effect, Integer.parseInt(sCurrentLine.substring(aInd, aInd+2).replaceAll("\\s+","")));

                }
                else if(sCurrentLine.substring(qInd+2, qInd+3).equals("S") )
                {

                    opt1 = new Option(OptionType.SCHOOL_OPTION, sCurrentLine.substring(starInd, lineInd), effect, Integer.parseInt(sCurrentLine.substring(aInd, aInd+2).replaceAll("\\s+","")));
                }
                else if(sCurrentLine.substring(qInd+2, qInd+3).equals("L") )
                {

                    opt1 = new Option(OptionType.LIBRARY_OPTION, sCurrentLine.substring(starInd, lineInd), effect, Integer.parseInt(sCurrentLine.substring(aInd, aInd+2).replaceAll("\\s+","")));
                }
                else if(sCurrentLine.substring(qInd+2, qInd+3).equals("N") )
                {

                    opt1 = new Option(OptionType.NIGHT_CLUB_OPTION, sCurrentLine.substring(starInd, lineInd), effect, Integer.parseInt(sCurrentLine.substring(aInd, aInd+2).replaceAll("\\s+","")));
                }
                else if(sCurrentLine.substring(qInd+2, qInd+3).equals("C") )
                {

                    opt1 = new Option(OptionType.CAFE_OPTION, sCurrentLine.substring(starInd, lineInd), effect, Integer.parseInt(sCurrentLine.substring(aInd, aInd+2).replaceAll("\\s+","")));
                }


                effect.makeStatsZero();

                effect.setStatByIndex(StatType.HEALTH, Integer.parseInt(sCurrentLine.substring(colInd+1, colInd+3).replaceAll("\\s+","")));
                effect.setStatByIndex(StatType.SOCIALITY, Integer.parseInt(sCurrentLine.substring(colInd+4, colInd+6).replaceAll("\\s+","")));
                effect.setStatByIndex(StatType.GRADES, Integer.parseInt(sCurrentLine.substring(colInd+7, colInd+9).replaceAll("\\s+","")));
                effect.setStatByIndex(StatType.MONEY, Integer.parseInt(sCurrentLine.substring(colInd+10, colInd+12).replaceAll("\\s+","")));

                if(sCurrentLine.substring(qInd+3, qInd+4).equals("H"))
                {
                    opt2 = new Option(OptionType.HOUSE_OPTION, sCurrentLine.substring(lineInd, qInd), effect, Integer.parseInt(sCurrentLine.substring(aInd+3, aInd+5).replaceAll("\\s+","")));

                }
                else if(sCurrentLine.substring(qInd+3, qInd+4).equals("S") )
                {
                    opt2 = new Option(OptionType.SCHOOL_OPTION, sCurrentLine.substring(lineInd, qInd), effect, Integer.parseInt(sCurrentLine.substring(aInd+3, aInd+5).replaceAll("\\s+","")));

                }
                else if(sCurrentLine.substring(qInd+3, qInd+4).equals("L") )
                {
                    opt2 = new Option(OptionType.LIBRARY_OPTION, sCurrentLine.substring(lineInd, qInd), effect, Integer.parseInt(sCurrentLine.substring(aInd+3, aInd+5).replaceAll("\\s+","")));

                }
                else if(sCurrentLine.substring(qInd+3, qInd+4).equals("N") )
                {
                    opt2 = new Option(OptionType.NIGHT_CLUB_OPTION, sCurrentLine.substring(lineInd, qInd), effect, Integer.parseInt(sCurrentLine.substring(aInd+3, aInd+5).replaceAll("\\s+","")));

                }
                else if(sCurrentLine.substring(qInd+3, qInd+4).equals("C") )
                {
                    opt2 = new Option(OptionType.CAFE_OPTION, sCurrentLine.substring(lineInd, qInd), effect, Integer.parseInt(sCurrentLine.substring(aInd+3, aInd+5).replaceAll("\\s+","")));

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

                if (fr != null)
                    fr.close();

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

    public void connectEvents(){
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(FILE_NAME);
            br = new BufferedReader(fr);

            String sCurrentLine;
            int index = 0;
            while ((sCurrentLine = br.readLine()) != null && index < MAX_EVENT_COUNT) {
                int lInd    = sCurrentLine.indexOf('~');
                List<Event> neighbors =  new LinkedList<Event>();
                Event cur   = allEvents[index];
                int left    = Integer.parseInt(sCurrentLine.substring(lInd+1, lInd+4).replaceAll("\\s+",""))-1;
                int right   = Integer.parseInt(sCurrentLine.substring(lInd+3, lInd+5).replaceAll("\\s+",""))-1;
                neighbors.add(allEvents[left]);
                neighbors.add(allEvents[right]);
                eventCollection.setNeighbors(cur, neighbors);
                index ++;
            }
            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

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
    public static void main(String[] args){
        Events e = new Events();
        //System.out.println(e.start);
        System.out.println(e.eventCollection.getNeighbors(e.start));
        System.out.println(e);
        System.out.println(e.eventCollection);
    }

}