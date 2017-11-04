package src;

import android.content.ClipData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by eliztekcan on 26.10.2017.
 */
public class ItemCollection {

    public static final int MAX_ITEM = 12;
    Item[] items;
    int[] boost;
    static final String FILE_NAME= "/Users/eliztekcan/Desktop/RoomieLogic/src/Other/Backpack.txt";

    ItemCollection(){
        items = new Item[MAX_ITEM];
        boost = new int[4];
        int[] boost = new int[4];
        //Add items to collection
        createCollection();
    }

    private void setBoostArray(int health, int sociality, int grades, int money) {
        //health
        boost[0] = health;
        //sociality
        boost[1] = sociality;
        //grades
        boost[2] = grades;
        //money
        boost[3] = money;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return items;
    }

    public void createCollection(){
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(FILE_NAME);
            br = new BufferedReader(fr);

            String sCurrentLine;
            int index = 0;
            while ((sCurrentLine = br.readLine()) != null && index < MAX_ITEM) {
                int starInd = sCurrentLine.indexOf('*');

                setBoostArray(Integer.parseInt(sCurrentLine.substring(starInd+1,starInd+3).replaceAll("\\s+","")),
                        Integer.parseInt(sCurrentLine.substring(starInd+4,starInd+6).replaceAll("\\s+","")),
                        Integer.parseInt(sCurrentLine.substring(starInd+7,starInd+9).replaceAll("\\s+","")),
                        Integer.parseInt(sCurrentLine.substring(starInd+10,starInd+12).replaceAll("\\s+","")));
                items[index] = new Item(sCurrentLine.substring(0,starInd), null, Integer.parseInt(sCurrentLine.substring(starInd+12).replaceAll("\\s+","")), boost);
                index++;
                boost = new int[4];

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

    }

    //for testing
    /*public static void main(String[] args){
        ItemCollection i = new ItemCollection();
        i.readFile();
        for(int k = 0; k< 12; k++)
            System.out.println(i.items[k]);
    }*/
}