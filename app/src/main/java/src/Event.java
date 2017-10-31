package src;

/**
 * Created by eliztekcan on 30.10.2017.
 */

public class Event {
    String question;
    Option[] optionArray;
    public static final int MAX_OPTION = 2;

    Event(){
        optionArray = new Option[MAX_OPTION];
    }

    public Option[] getOptionArray() {
        return optionArray;
    }

    public void setOptionArray(Option[] optionArray) {
        this.optionArray = optionArray;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public static int getMaxOption() {
        return MAX_OPTION;
    }
}
