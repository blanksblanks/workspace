import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Hashtable;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graph extends JPanel {

	final int RADIUS = 10;

	private LinkedList<String> names;
	private Hashtable<String, Vertex> hash;
	private LinkedList<Edge> edges;
	public LinkedList<Vertex> path;
	public String route;
	private double totalDistance;
	private double shortestDistance;
    private LinkedList<Edge> mst;
    private boolean edgesProvided;

	public Graph(LinkedList<String> pairs, LinkedList<Double> distances,
			LinkedList<String> cityNames, LinkedList<Integer> xy) {

		names = cityNames;
		hash = new Hashtable<String, Vertex>();

		int i = 0;
		String[] namesArray = new String[names.size()];
		Iterator<String> namesIterator = names.iterator();
		Iterator<Integer> xyIterator = xy.iterator();

		while (namesIterator.hasNext()) {
			String cityName = namesIterator.next();
			Vertex city = new Vertex(cityName, xyIterator.next(),
					xyIterator.next());
			hash.put(cityName, city); // put string and vertex into hash table
			namesArray[i] = cityName; i++; // copy into array
		}

		if (pairs != null) {
			buildSparseGraph(pairs, distances);
			edgesProvided = true;
		} else {
			buildCompleteGraph(namesArray);
			edgesProvided = false;
		}
		
	}

	private void buildSparseGraph(LinkedList<String> pairs, LinkedList<Double> distances){
		// pull cities out in pairs
		Iterator<String> pairsIterator = pairs.iterator();
		Iterator<Double> distancesIterator = distances.iterator();
		while (pairsIterator.hasNext()) {
			Vertex v1 = hash.get(pairsIterator.next());
			Vertex v2 = hash.get(pairsIterator.next());
			Double distance = distancesIterator.next();
			Edge e1 = new Edge(v1, v2, distance);
			Edge e2 = new Edge(v2, v1, distance);
			v1.addEdge(e1);
			v2.addEdge(e2);
			totalDistance += distance;
		}
	}
	
	private void buildCompleteGraph(String[] arr) {
		edges = new LinkedList<Edge>();
		
		for (int i = 0; i < arr.length; i++){
			Vertex v1 = hash.get(arr[i]);
			for (int j = i+1; j < arr.length; j++){
				Vertex v2 = hash.get(arr[j]);
				Edge e = new Edge(v1, v2, calculateEuclidDistance(v1, v2));
				v1.addEdge(e);
				edges.add(e);
			}
		}
	}

	// Based on Weiss pseudocode
	public String dijkstra(String origin, String destination)
			throws UnderflowException {
		
		route = ""; // reset here so it doesn't print previous values for invalid input
		
		if (origin.equals("") || destination.equals(""))
			return ("One of your fields is blank. Please try again.");

		if (origin.equals(destination))
			return ("No need to travel because your point of origin is your destination.");

		BinaryHeap<Vertex> heap = new BinaryHeap<Vertex>(names.size());
		Vertex start = hash.get(origin);
		Vertex end = hash.get(destination);

		if (start == null || end == null)
			return ("One of these cities is not in the map. Please try again.");

		// for each vertex v, reset
		Iterator<String> namesIterator = names.iterator();
		while (namesIterator.hasNext()) {
			Vertex v = hash.get(namesIterator.next());
			v.distance = totalDistance; // INFINITY
			v.known = false;
			v.previous = null;
		}

		start.distance = 0;
		start.known = true;
		heap.insert(start);

		// while there is an unknown distance vertex
		while (!heap.isEmpty()) {
			Vertex v = heap.deleteMin();
			Iterator<Edge> adjIterator = v.adjacencyList.iterator();
			// Hm... can directly access like this
			while (adjIterator.hasNext()) {
				Edge e = adjIterator.next();
				Vertex w = e.v2;
				if (!w.known) { //
					double cvw = e.weight; // cost of edge from v to w
					if ((v.distance + cvw) < w.distance) { // update w
						w.distance = v.distance + cvw;
						w.previous = v;
						heap.insert(w);
					}
				}
			}
		}
		
		path = new LinkedList<Vertex>();
		shortestDistance = end.distance;
		buildPath(end);

		String shortestDist = "" + shortestDistance;
		return shortestDist;
	}
	
	private void buildPath(Vertex v) {
		if (v.previous != null) {
			buildPath(v.previous);
			path.add(v.previous);
			route += " -> ";
		}
		path.add(v);
		route += v.toString();
	}

	// Testing method
	@SuppressWarnings("unused")
	private String printPath() {
		String s = "";
		Iterator<Vertex> pathIterator = path.iterator();
		while (pathIterator.hasNext()) {
			s += " " + pathIterator.next();
		}
		return s;
	}

	private double calculateEuclidDistance(Vertex v1, Vertex v2) {      
        double base = Math.abs(v1.x - v2.x); // x1 - x2
        double height = Math.abs(v1.y - v2.y); // y1 - y2
        double hypotenuse = Math.sqrt((Math.pow(base, 2) + (Math.pow(height, 2))));
        return hypotenuse;
    }
	
	// Based on Weiss pseudocode
	public void kruskal() {
		DisjSets ds = new DisjSets(edges.size());
		mst = new LinkedList<Edge>();
		BinaryHeap<Edge> heap = new BinaryHeap<Edge>(edges.toArray());
		System.out.println(heap.getCurrentSize());
		while (mst.size() != names.size() - 1 && !heap.isEmpty()) {
			Edge e = heap.deleteMin();
//			System.out.println(e.v1);
			int uset = ds.find(myhash(e.v1.name));
			int vset = ds.find(myhash(e.v2.name));
			System.out.println(e.v1.name + " and " + e.v2.name + " inputs: " + myhash(e.v1.name) + " " + myhash(e.v2.name) + " " + uset + " " + vset);
			if (uset != vset){
				mst.add(e);
				ds.union(uset, vset);
			}	
		}
	}
	
	// Hashes the city name to create unique number keys
	private int myhash(String key) {
		int hashVal = 0;
		for (int i = 0; i < key.length(); i += 2)
			hashVal = 37 * hashVal + key.charAt(i);
		hashVal %= edges.size();
		if (hashVal < 0)
			hashVal += edges.size(); 
		return hashVal;
	}	
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		@SuppressWarnings("unused")
		Color mint = new Color(162, 255, 204);
		Color gray = new Color(224, 224, 224);
		Color random;
		g2.setFont(new Font("Century Gothic", Font.BOLD, 12));
		g2.setStroke(new BasicStroke(5));

		Iterator<String> namesIterator = names.iterator();

		// Draw all the lines first
		if (edgesProvided) {
			while (namesIterator.hasNext()) {
				g2.setColor(gray);
				Vertex vertex = hash.get(namesIterator.next());
				LinkedList<Edge> adjacentCities = vertex.adjacencyList;
				Iterator<Edge> adjIterator = adjacentCities.iterator();
				while (adjIterator.hasNext()) {
					Edge e = adjIterator.next();
					g2.drawLine(e.x1, e.y1, e.x2, e.y2);
				}
			}
		}
		
		// Redraw green route if Dijkstra was performed
		if (path != null){
			g2.setColor(Color.GREEN);
			g2.setStroke(new BasicStroke(3));
			Iterator<Vertex> pathIterator = path.iterator();
			pathIterator.next(); // skip the first element
			// for (int i = 0; i < (path.size()/2)-1; i++){
			while (pathIterator.hasNext()) {
				Vertex v1 = pathIterator.next();
				Vertex v2 = pathIterator.next();
				g2.drawLine(v1.x, v1.y, v2.x, v2.y);
			}
			path = null; // reset path
		}
		
		if (mst != null){
			g2.setColor(gray);
			Iterator<Edge> mstIterator = mst.iterator();
			while (mstIterator.hasNext()){
				Edge e = mstIterator.next();
				g2.drawLine(e.x1 , e.y1, e.x2, e.y2);
			}
		}

		// Reinitialize iterator
		namesIterator = names.iterator();
		// Draw all the nodes and labels
		while (namesIterator.hasNext()) {
			Vertex vertex = hash.get(namesIterator.next());
			String label = vertex.toString();
			int x = vertex.x;
			int y = vertex.y;

			g2.setColor(Color.BLACK);
			g2.drawString(label, x + 15, y + 5);

			random = mixRandomColorWith(Color.CYAN);
			g2.setColor(random);
			Ellipse2D.Double city = new Ellipse2D.Double(x - RADIUS,
					y - RADIUS, RADIUS * 2, RADIUS * 2);
			g2.fill(city);
			g2.draw(city);
		}
		
	}

	private Color mixRandomColorWith(Color mix) {
		Random random = new Random();
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		// mix the color
		if (mix != null) {
			r = (r + mix.getRed()) / 2;
			g = (g + mix.getGreen()) / 2;
			b = (b + mix.getBlue()) / 2;
		}
		Color color = new Color(r, g, b);
		return color;
	}

}
