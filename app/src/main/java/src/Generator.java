package src;

import android.content.res.Resources;

import com.sun.tools.javah.Gen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import src.Enums.OptionType;
import src.Enums.RoomType;
import src.Enums.StatType;

/**
 * Created by eliztekcan on 18.12.2017.
 */

public class Generator {

    final static int MAX_EXTREME_EVENT_COUNT_HOUSE = 3;
    final static int MAX_EXTREME_EVENT_COUNT_OUTDOOR = 2;
    final static int MAX_POSSIBLE_ENDS = 7;

    public static int idGenerator(OptionType option, RoomType room, Enum item) {
        if (option == OptionType.HOUSE_OPTION) {
            int first = option.ordinal() * 100;
            int second = room.ordinal() * 10;
            int third = item.ordinal();
            return first + second + third;
        } else {
            return option.ordinal() * 100;
        }
    }

    public static String extremeEventGenerator(Resources r, String pn, OptionType opt) {

        BufferedReader br = null;
        InputStream in = null;
        String[] extremes_house = new String[MAX_EXTREME_EVENT_COUNT_HOUSE];
        String[] extremes_outdoor = new String[MAX_EXTREME_EVENT_COUNT_OUTDOOR];

        try {
            in = r.openRawResource(r.getIdentifier("extremeevent", "raw", pn));
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String sCurrentLine;


            int index = 0;
            while ((sCurrentLine = br.readLine()) != null && index < MAX_EXTREME_EVENT_COUNT_HOUSE + MAX_EXTREME_EVENT_COUNT_OUTDOOR) {
                if( index < MAX_EXTREME_EVENT_COUNT_HOUSE)
                    extremes_house[index] = sCurrentLine;
                else
                    extremes_outdoor[index - MAX_EXTREME_EVENT_COUNT_OUTDOOR-1] = sCurrentLine;
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
        if( opt == OptionType.HOUSE_OPTION) {
            return extremes_house[(int) (Math.random() * MAX_EXTREME_EVENT_COUNT_HOUSE)];
        }
        else
            return extremes_outdoor[(int) (Math.random() * MAX_EXTREME_EVENT_COUNT_OUTDOOR)];

    }

    public static String endGenerator(Resources r, String pn, Stats currentStats)
    {
        BufferedReader br = null;
        InputStream in = null;
        String[] possible_ends = new String[MAX_POSSIBLE_ENDS];
        Stats[] treshold = new Stats[MAX_POSSIBLE_ENDS];

        try {
            in = r.openRawResource(r.getIdentifier("possibleends", "raw", pn));
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String sCurrentLine;


            int index = 0;
            while ((sCurrentLine = br.readLine()) != null && index < MAX_POSSIBLE_ENDS)
            {
                int ampInd = sCurrentLine.indexOf('&');
                possible_ends[index] = sCurrentLine.substring(0, ampInd);
                int[] arr = new int[4];
                arr[0] = Integer.parseInt(sCurrentLine.substring(ampInd+1,ampInd+2));
                arr[1] = Integer.parseInt(sCurrentLine.substring(ampInd+4,ampInd+5));
                arr[2] = Integer.parseInt(sCurrentLine.substring(ampInd+6,ampInd+7));
                arr[3] = Integer.parseInt(sCurrentLine.substring(ampInd+8,ampInd+9));
                System.out.print(arr);
                treshold[index] = new Stats(arr);
                index++;
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


        for ( int i = 0; i < MAX_POSSIBLE_ENDS; i++){
            if (compareWithTreshold(treshold, currentStats, i)) {
                //System.out.println(possible_ends[i]);
                return possible_ends[i];
            }
        }
        return possible_ends[0];

    }

    private static boolean compareWithTreshold(Stats[] treshold, Stats currentStats, int i) {
        //System.out.println(treshold[i]);
        //System.out.println(currentStats);

        if (currentStats.getStatByIndex(StatType.HEALTH) < treshold[i].getStatByIndex(StatType.HEALTH)) {
            return false;
        }
        if (currentStats.getStatByIndex(StatType.SOCIALITY) < treshold[i].getStatByIndex(StatType.SOCIALITY)) {
            return false;
        }
        if (currentStats.getStatByIndex(StatType.GRADES) < treshold[i].getStatByIndex(StatType.GRADES)) {
            return false;
        }
        if (currentStats.getStatByIndex(StatType.MONEY) < treshold[i].getStatByIndex(StatType.MONEY)) {
            return false;
        }
        return true;
    }


    //test
    /*public static void main(String[] args){
        Gen e = new Events();
        //System.out.println(e.start);
        System.out.println(e.eventCollection.getNeighbors(e.start));
        System.out.println(e);
        System.out.println(e.eventCollection);
    }*/

}