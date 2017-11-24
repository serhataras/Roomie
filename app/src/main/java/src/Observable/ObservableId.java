package src.Observable;

import java.util.Observable;

/**
 * Created by alkislar on 22.11.2017.
 */

public class ObservableId extends Observable
{
    // id to observe
    private int id;

    // constructor
    public ObservableId(int id)
    {
        this.id = id;
    }

    // methods
    public void setValue(int n)
    {
        this.id = id;
        setChanged();
        notifyObservers();
    }

    public int getValue()
    {
        return id;
    }
}

