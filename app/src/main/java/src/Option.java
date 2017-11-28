package src;

import java.util.Arrays;

/**
 * Created by eliztekcan on 26.10.2017.
 */
public class Option {
    private OptionType optionType;
    private String id;
    private boolean isExtreme;
    private int effect[];

    Option(){
        effect = new int[4];
    }

    Option(OptionType optionType, String id, boolean isExtreme, int effect[]){
        this.optionType = optionType;
        this.id         = id;
        this.isExtreme  = isExtreme;
        this.effect     = effect;
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public void setOptionType(OptionType optionType) {
        this.optionType = optionType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
