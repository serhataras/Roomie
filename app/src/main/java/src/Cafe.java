package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by eliztekcan on 27.10.2017.
 */
public class Cafe extends Outdoor
{
    private FoodItem[] menu;
    private final int MAX_FOOD = 10;
    private final String FILE_NAME = "raw/menu.txt";
    public FoodItem selectedFood;
    public Cafe()
    {
        menu = new FoodItem[MAX_FOOD];
        createMenu();
    }

    public Cafe(FoodItem[] menu)
    {
        this.menu = menu;
    }

    private void createMenu() {
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(FILE_NAME);
            br = new BufferedReader(fr);

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

                if (fr != null)
                    fr.close();

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
    public static void main(String[] args){
        Cafe cafe = new Cafe();
        for(int k = 0; k< 10; k++)
            System.out.println(cafe.menu[k]);
    }

}