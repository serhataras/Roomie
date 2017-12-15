package src;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by eliztekcan on 31.10.2017.
 */

class Graph {
    private Map<Event, List<Event>> adjacencyList;
    private int vertices;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new HashMap<Event, List<Event>>();

        /*for (int i = 0; i < vertices; ++i) {
            adjacencyList.put(allEvents[i], new LinkedList<Event>());
        }*/
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

    public List<Event> getNeighbors(Event event){
        return adjacencyList.get(event);
    }

    public void setNeighbors(Event event, List<Event> neighbors){
        System.out.println(event + " " + neighbors);
        adjacencyList.put(event, neighbors);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "adjacencyList=" + adjacencyList +
                '}';
    }

    //test

    public static void main(String args[])
    {
        Option[] arr = new Option[2];

        arr[0] = new Option();
        arr[1] = new Option();

        Event a = new Event("Event a", arr);
        Event b = new Event("Event b", arr);
        Event[] events = new Event[2];
        events[0] = a;
        events[1] = b;
        Graph g = new Graph(2);
        g.setEdge(a,b);

        System.out.print(g);
    }
}
