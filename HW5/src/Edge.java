
public class Edge implements Comparable<Edge>{

	public int x1;
	public int x2;
	public int y1;
	public int y2;
	public double weight;
	public Vertex v1;
	public Vertex v2;
	
	public Edge(Vertex vertex1, Vertex vertex2, double distance){
		v1 = vertex1;
		v2 = vertex2;
		x1 = vertex1.x;
		y1 = vertex1.y;
		x2 = vertex2.x;
		y2 = vertex2.y;
		weight = distance;
	}
	
	public int compareTo(Edge e) {
		if (this.weight < e.weight) return -1;
		else if (this.weight > e.weight) return 1;
		else return 0;
	}
	
	public String toString(){
		return v1 + " " + v2;
	}
}
