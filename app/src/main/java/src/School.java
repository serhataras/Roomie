package src;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by eliztekcan on 28.10.2017 edited by serhat
 */

public class School extends Outdoor
{
    private static final int MAX_QUESTION = 4;

    private QuizQuestion[] questions;
    private String[] options;
    private int random;
    private String selectedAnswer;

    public School(Resources r, String pn)
    {
        super();
        questions   = new QuizQuestion[MAX_QUESTION];
        options = new String[4];
        initializeQuestions(r, pn);
        random = 0;
        selectedAnswer = "";
    }

    private void initializeQuestions(Resources r, String pn)
    {
        BufferedReader br = null;
        InputStream in = null;

        try
        {
            in = r.openRawResource(r.getIdentifier("quiz", "raw", pn));
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String sCurrentLine;
            int index = 0;
            while ((sCurrentLine = br.readLine()) != null && index < MAX_QUESTION)
            {
                int starInd = sCurrentLine.indexOf('?');

                setOptionsArray(sCurrentLine.substring(starInd+1,starInd+3), sCurrentLine.substring(starInd+4,starInd+6),
                        sCurrentLine.substring(starInd+7,starInd+9), sCurrentLine.substring(starInd+10,starInd+12));
                questions[index] = new QuizQuestion(options, Integer.parseInt(sCurrentLine.substring(starInd+12).replaceAll("\\s+","")) -1, sCurrentLine.substring(0,starInd));
                index++;
                options = new String[4];

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();

        }
        finally
        {
            try
            {
                if (br != null)
                    br.close();

                if (in != null)
                    in.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }

        }
    }

    private void setOptionsArray(String i, String i1, String i2, String i3)
    {
        options[0] = i;
        options[1] = i1;
        options[2] = i2;
        options[3] = i3;
    }

    public QuizQuestion getRandomQuestion()
    {
        random = (int) (Math.random() * MAX_QUESTION);
        return questions[random];
    }


    @Override
    public void updateChallengeSuccess()
    {
        QuizQuestion currentQuestion = questions[random];
        String correctAnswer = currentQuestion.getOptions()[currentQuestion.getCorrectAnswerIndex()];

        if(correctAnswer.equalsIgnoreCase(getSelectedAnswer()))
        {
            super.setChallengeSuccess(true);
        }
        else
        {
            super.setChallengeSuccess(false);
        }

    }

    public String getSelectedAnswer()
    {
        return selectedAnswer;
    }

    public QuizQuestion getCurrentRandomQuestion()
    {
        return questions[random];
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    /*public static void main(String[] args){
        School s = new School();
        for(int k = 0; k< MAX_QUESTION; k++)
            System.out.println(s.questions[k]);
    }*/
}
