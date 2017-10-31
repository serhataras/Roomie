package src;

import android.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by eliztekcan on 31.10.2017.
 */

class Graph {
    private Map<Event, List<Event>> adjacencyList;

    public Graph(int vertices) {
        adjacencyList = new HashMap<Event, List<Event>>();

        for (int i = 0; i < vertices; ++i) {
            adjacencyList.put(new Event(), new LinkedList<Event>());
        }
    }

    public void setEdge(Event to, Event from){

        List<Event> sls = adjacencyList.get(to);
        sls.add(from);

        List<Event> dls = adjacencyList.get(from);
        dls.add(to);
    }

    public List<Event> getEdge(int to)
    {
        return adjacencyList.get(to);
    }
}
