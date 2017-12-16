package src.Observable;

import java.util.Observable;

import src.Event;

/**
 * Created by alkislar on 12.12.2017.
 */

public class ObservableEvent extends Observable
{
    // id to observe
    private Event event;

    // constructor
    public ObservableEvent(Event event)
    {
        this.event = event;
    }

    // methods
    public void setValue(Event event)
    {
        this.event = event;
        setChanged();
        notifyObservers();
    }

    public Event getValue()
    {
        return event;
    }
}
