package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by eliztekcan on 30.10.2017.
 */

public class Events
{
    private Graph eventCollection;
    private Event[] allEvents;
    private static final int MAX_EVENT_COUNT = 6;
    private static final String FILE_NAME = "/Users/eliztekcan/StudioProjects/Roomie/app/src/main/java/src/Other/Events.txt";


    Events(){
        allEvents = setEvents();
        eventCollection = new Graph(MAX_EVENT_COUNT, allEvents);
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
                Option opt1 = null, opt2 = null;
                int[] effect = new int[4];
                effect[0] = Integer.parseInt(sCurrentLine.substring(eqInd+1, eqInd+3).replaceAll("\\s+",""));
                effect[1] = Integer.parseInt(sCurrentLine.substring(eqInd+4, eqInd+6).replaceAll("\\s+",""));
                effect[2] = Integer.parseInt(sCurrentLine.substring(eqInd+7, eqInd+9).replaceAll("\\s+",""));
                effect[3] = Integer.parseInt(sCurrentLine.substring(eqInd+10, eqInd+12).replaceAll("\\s+",""));


                if(sCurrentLine.substring(qInd+2, qInd+3).equals("H") )
                {
                    if(sCurrentLine.substring(aInd+1, aInd+2).equals("1")){
                        opt1 = new Option(OptionType.HOUSE_OPTION, sCurrentLine.substring(starInd, lineInd), true, effect);
                    }
                    else if (sCurrentLine.substring(aInd+1, aInd+2).equals("0")) {
                        opt1 = new Option(OptionType.HOUSE_OPTION, sCurrentLine.substring(starInd, lineInd), false, effect);
                    }
                }
                else if(sCurrentLine.substring(qInd+2, qInd+3).equals("O") )
                {
                    if(sCurrentLine.substring(aInd+1, aInd+2).equals("1")){
                        opt1 = new Option(OptionType.OUTDOOR_OPTION, sCurrentLine.substring(starInd, lineInd), true, effect);
                    }
                    else if (sCurrentLine.substring(aInd+1, aInd+2).equals("0")){
                        opt1 = new Option(OptionType.OUTDOOR_OPTION, sCurrentLine.substring(starInd, lineInd), false, effect);
                    }
                }


                effect = new int[4];

                effect[0] = Integer.parseInt(sCurrentLine.substring(colInd+1, colInd+3).replaceAll("\\s+",""));
                effect[1] = Integer.parseInt(sCurrentLine.substring(colInd+4, colInd+6).replaceAll("\\s+",""));
                effect[2] = Integer.parseInt(sCurrentLine.substring(colInd+7, colInd+9).replaceAll("\\s+",""));
                effect[3] = Integer.parseInt(sCurrentLine.substring(colInd+10).replaceAll("\\s+",""));

                if(sCurrentLine.substring(qInd+3, qInd+4).equals("H"))
                {
                    if(sCurrentLine.substring(aInd+3, aInd+4).equals("1"))
                        opt2 = new Option(OptionType.HOUSE_OPTION, sCurrentLine.substring(lineInd, qInd), true, effect);
                    else if (sCurrentLine.substring(aInd+3, aInd+4).equals("0"))
                        opt2 = new Option(OptionType.HOUSE_OPTION, sCurrentLine.substring(lineInd, qInd), false, effect);
                }
                else if(sCurrentLine.substring(qInd+3, qInd+4).equals("O") )
                {
                    if(sCurrentLine.substring(aInd+3, aInd+4).equals("1")) {
                        System.out.print(sCurrentLine.substring(aInd + 3, aInd + 4));
                        opt2 = new Option(OptionType.OUTDOOR_OPTION, sCurrentLine.substring(lineInd, qInd), true, effect);
                    }
                    else if (sCurrentLine.substring(aInd+3, aInd+4).equals("0")) {
                        opt2 = new Option(OptionType.OUTDOOR_OPTION, sCurrentLine.substring(lineInd, qInd), false, effect);
                    }
                }
                Option[] arr = new Option[Event.getMaxOption()];
                arr[0] = opt1;
                arr[1] = opt2;
                allEvents[index] = new Event(sCurrentLine.substring(0, starInd),arr);
                index++;

            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

        return allEvents;
    }


    public Graph getEventCollection() {
        return eventCollection;
    }

    public void setEventCollection(Graph eventCollection) {
        this.eventCollection = eventCollection;
    }


    @Override
    public String toString() {
        return "Events{" +
                "eventCollection=" + eventCollection +
                //", allEvents=" + Arrays.toString(allEvents) +
                '}';
    }

    //test
    /*public static void main(String[] args){
        Events e = new Events();
        System.out.println(e);
    }*/

}
