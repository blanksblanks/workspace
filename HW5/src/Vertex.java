import java.util.LinkedList;

public class Vertex implements Comparable<Vertex> {

	public String name;
	public int x;
	public int y;
	public boolean known;
	public double distance;
	public Vertex previous;
	public LinkedList<Edge> adjacencyList;
	
	public Vertex(String name, int x, int y){
		this.name = name;
		this.x = x;
		this.y = y;
		adjacencyList = new LinkedList<Edge>();
		previous = null;
	}
	
	public void addEdge(Edge e){
		this.adjacencyList.add(e);
	}
	
	public int compareTo(Vertex v){
		if (this.distance < v.distance) return -1;
		else if (this.distance > v.distance) return 1;
		else return 0;
	}
	
	public String toString(){
		if (name.equals("NewYork"))
			return "New York";
		else if (name.equals("SaltLake"))
			return "Salt Lake";
		else if (name.equals("St.Louis"))
			return "St. Louis";
		else if (name.equals("SanFrancisco"))
			return "San Francisco";
		else if (name.equals("LasVegas"))
			return "Las Vegas";
		else if (name.equals("LosAngeles"))
			return "Los Angeles";
		else if (name.equals("NewOrleans"))
			return "New Orleans";
		else return name;
	}
	
}
