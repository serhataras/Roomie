package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by eliztekcan on 28.10.2017.
 */
public class School extends Outdoor
{
    QuizQuestion[] questions;
    String[] options;
    int random;
    public static final int MAX_QUESTION = 4;
    static final String FILE_NAME= "/src/Other/Quiz.txt";


    School()
    {
        int random  = 0;
        options = new String[4];
        questions   = new QuizQuestion[MAX_QUESTION];
        initializeQuestions();
    }

    private void initializeQuestions()
    {
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(FILE_NAME);
            br = new BufferedReader(fr);

            String sCurrentLine;
            int index = 0;
            while ((sCurrentLine = br.readLine()) != null && index < MAX_QUESTION) {
                int starInd = sCurrentLine.indexOf('?');

                setOptionsArray(sCurrentLine.substring(starInd+1,starInd+3), sCurrentLine.substring(starInd+4,starInd+6),
                        sCurrentLine.substring(starInd+7,starInd+9), sCurrentLine.substring(starInd+10,starInd+12));
                questions[index] = new QuizQuestion(options, Integer.parseInt(sCurrentLine.substring(starInd+12).replaceAll("\\s+","")), sCurrentLine.substring(0,starInd));
                index++;
                options = new String[4];

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

    private void setOptionsArray(String i, String i1, String i2, String i3) {
        options[0] = i;
        options[1] = i1;
        options[2] = i2;
        options[3] = i3;
    }

    //test

    public static void main(String[] args){
        School s = new School();
        for(int k = 0; k< MAX_QUESTION; k++)
            System.out.println(s.questions[k]);
    }

}
