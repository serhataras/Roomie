package src;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class Cafe extends Outdoor
{
    private FoodItem[] menu;
    private final int MAX_FOOD = 10;
    public FoodItem selectedFood;

    public Cafe(Resources r, String pn)
    {
        super();
        menu = new FoodItem[MAX_FOOD];
        createMenu(r, pn);
    }

    public Cafe(FoodItem[] menu)
    {
        this.menu = menu;
    }

    private void createMenu(Resources r, String pn) {
        BufferedReader br = null;
        InputStream in = null;

        try {
            in = r.openRawResource(r.getIdentifier("menu", "raw", pn));
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String sCurrentLine;
            int index = 0;
            while ((sCurrentLine = br.readLine()) != null && index < MAX_FOOD) {
                int starInd = sCurrentLine.indexOf('*');

                menu[index] = new FoodItem(sCurrentLine.substring(0,starInd), Integer.parseInt(sCurrentLine.substring(starInd+1,starInd+3).replaceAll("\\s+","")), Integer.parseInt(sCurrentLine.substring(starInd+4).replaceAll("\\s+","")));
                index++;
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (in != null)
                    in.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }

    public FoodItem[] getMenu() {
        return menu;
    }

    public void setMenu(FoodItem[] menu) {
        this.menu = menu;
    }

    public int getMAX_FOOD() {
        return MAX_FOOD;
    }

    public FoodItem getSelectedFood() {
        return selectedFood;
    }

    public void setSelectedFood(FoodItem selectedFood) {
        this.selectedFood = selectedFood;
    }

    //for testing
    /*public static void main(String[] args){
        Cafe cafe = new Cafe();
        for(int k = 0; k< 10; k++)
            System.out.println(cafe.menu[k]);
    }*/

}