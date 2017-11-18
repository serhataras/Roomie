package com.threek.roomie.Game;

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

    public Event(String question, Option[] optionArray){
        this.optionArray = optionArray;
        this.question = question;
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

    @Override
    public String toString() {
        return "Event{" +
                "question='" + question + '\'' +
                //", optionArray=" + Arrays.toString(optionArray) +
                '}';
    }
}
