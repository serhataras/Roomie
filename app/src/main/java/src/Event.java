package src;

import src.Enums.OptionType;

/**
 * Created by eliztekcan on 30.10.2017.
 */

public class Event {
    private String question;
    private Option[] optionArray;
    private  static final int MAX_OPTION = 2;

    public Event(){
        optionArray = new Option[MAX_OPTION];
    }

    public Event(String question, Option[] optionArray){
        this.optionArray = optionArray;
        this.question = question;
    }

    public Option chooseAnOption(int id)
    {
        Option optionToReturn = null;

        for (int i = 0; i < MAX_OPTION; i++)
        {
            if (optionArray[i].getId() == id)
            {
                optionToReturn = optionArray[i];
                break;
            }
        }
        return optionToReturn;
    }

    public boolean isOptionExtreme(int id)
    {
        if (chooseAnOption(id) != null)
            return chooseAnOption(id).isExtreme();
        return false;
    }

    public OptionType whichOption(int id)
    {
        if (chooseAnOption(id) != null)
            return chooseAnOption(id).getOptionType();
        return null;
    }

    public int[] getOptionsId()
    {
        int[] ids = new int[MAX_OPTION];
        for (int i = 0; i < MAX_OPTION; i++)
        {
            ids[i] = optionArray[i].getId();
        }
        return ids;
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

    public Option[] getOptionArray() {
        return optionArray;
    }

    @Override
    public String toString() {
        return "Event{" +
                "question='" + question + '\'' +
                //", optionArray=" + Arrays.toString(optionArray) +
                '}';
    }
}