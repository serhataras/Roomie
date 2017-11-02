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

    public Graph(int vertices, Event[] allEvents) {
        adjacencyList = new HashMap<Event, List<Event>>();

        for (int i = 0; i < vertices; ++i) {
            adjacencyList.put(allEvents[i], new LinkedList<Event>());
        }
    }

    public void setEdge(Event to, Event from)
    {
        List<Event> sls = adjacencyList.get(to);
        sls.add(from);
    }

    public List<Event> getEdge(int to)
    {
        return adjacencyList.get(to);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "adjacencyList=" + adjacencyList +
                '}';
    }

    //test

    /*public static void main(String args[])
    {
        Option[] arr = new Option[2];

        arr[0] = new Option();
        arr[1] = new Option();

        Event a = new Event("Event a", arr);
        Event b = new Event("Event b", arr);
        Event[] events = new Event[2];
        events[0] = a;
        events[1] = b;
        Graph g = new Graph(2, events);
        g.setEdge(a,b);

        System.out.print(g);
    }*/
}
