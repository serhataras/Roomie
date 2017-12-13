package src;
import java.util.Arrays;

/**
 * Created by eliztekcan on 26.10.2017.
 */
public class Option {
    private OptionType optionType;
    private String optionStr;
    private int id;
    private boolean isExtreme;
    private int effect[];

    Option(){
        effect = new int[4];
    }

    Option(OptionType optionType,  String optionStr, int effect[], int id){
        this.optionType = optionType;
        this.optionStr  = optionStr;
        this.id         = id;
        this.effect     = effect;
        assignExtremeCase();
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public void setOptionType(OptionType optionType) {
        this.optionType = optionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isExtreme() {
        return isExtreme;
    }

    public void setExtreme(boolean extreme) {
        isExtreme = extreme;
    }

    public int[] getEffect() {
        return effect;
    }

    public void setEffect(int[] effect) {
        this.effect = effect;
    }

    public String getOptionStr() {
        return optionStr;
    }

    public void setOptionStr(String optionStr) {
        this.optionStr = optionStr;
    }

    public void assignExtremeCase(){
        //assign rand to a random number in range [1,10]
        int rand = 1 + (int)(Math.random()*10);
        //if the random number is 10, our option results in an extreme case
        if(rand == 10)
            isExtreme = true;
        else
            isExtreme = false;
    }

    @Override
    public String toString() {
        return "Option{" +
                "optionType=" + optionType +
                ", id='" + id + '\'' +
                ", isExtreme=" + isExtreme +
                ", effect=" + Arrays.toString(effect) +
                '}';
    }
}