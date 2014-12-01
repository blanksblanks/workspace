import java.util.Iterator;
import java.util.LinkedList;
import java.util.Hashtable;

public class Graph {
	
    private LinkedList<String> names;
    private Hashtable<String, Vertex> cities;
    private LinkedList<Edge> edges;

    private BinaryHeap<Edge> heap;
    private double totalDistance;
	
	public Graph(LinkedList<String> pairs, LinkedList<Double> distances,
			LinkedList<String> cityNames, LinkedList<Integer> xy){
		
		names = cityNames;
		cities = new Hashtable<String, Vertex>();
		
		Iterator<String> namesIterator = names.iterator();
		Iterator<Integer> xyIterator = xy.iterator();

		while (namesIterator.hasNext()){
			String cityName = namesIterator.next();
			Vertex city = new Vertex(cityName, xyIterator.next(), xyIterator.next());
			cities.put(cityName, city);
		}
		
		edges = new LinkedList<Edge>();
		
		Iterator<String> pairsIterator = pairs.iterator();
		Iterator<Double> distancesIterator = distances.iterator();
		while (pairsIterator.hasNext()){
			Vertex v1 = cities.get(pairsIterator.next());
			Vertex v2 = cities.get(pairsIterator.next());
			Double distance = distancesIterator.next();
			Edge e1 = new Edge(v1, v2, distance);
			Edge e2 = new Edge(v2, v1, distance);
			v1.addEdge(e1);
			v2.addEdge(e2);
			totalDistance += distance;
		}
			
		
		
	}

}
