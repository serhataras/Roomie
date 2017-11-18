package src;

import java.util.Arrays;

/**
 * Created by eliztekcan on 28.10.2017.
 */
public class QuizQuestion {
    String[] options;
    int correctAnswerIndex;
    String question;

    QuizQuestion()
    {
        options             = new String[4];
        correctAnswerIndex  = 0;
        question            = "";
    }

    QuizQuestion(String[] options, int correctAnswerIndex, String question)
    {
        this.options            = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.question           = question;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "QuizQuestion{" +
                "options=" + Arrays.toString(options) +
                ", correctAnswerIndex=" + correctAnswerIndex +
                ", question='" + question + '\'' +
                '}';
    }
}
