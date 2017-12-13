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
    private Stats effect;

    public Option(){
        effect = new Stats();
    }

    public Option(OptionType optionType,  String optionStr, Stats effect, int id){
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

    public Stats getEffect() {
        return effect;
    }

    public void setEffect(Stats effect) {
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
        int rand = 1 + (int) (Math.random() * 10);
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
                ", effect=" + effect.toString() +
                '}';
    }
}