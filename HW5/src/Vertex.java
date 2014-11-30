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
	
	public int compareTo(Vertex v){
		if (this.distance < v.distance) return -1;
		else if (this.distance > v.distance) return 1;
		else return 0;
	}
	
	public String toString(){
		return name;
	}
	
}
