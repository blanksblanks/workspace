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
public class Graph extends JPanel{
	
	final int RADIUS = 10;

    private LinkedList<String> names;
    private Hashtable<String, Vertex> cities;
//    private double totalDistance;
	
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
//			totalDistance += distance;
		}
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Color mint = new Color(162, 255, 204);
		Color gray = new Color(224, 224, 224);
		g2.setFont(new Font("Century Gothic", Font.BOLD, 12));
        g2.setStroke(new BasicStroke(5));
        g2.setColor(gray);	
        
		Iterator<String> namesIterator = names.iterator();

		while (namesIterator.hasNext()){
			Vertex vertex = cities.get(namesIterator.next());
			LinkedList<Edge> adjacentCities = vertex.adjacencyList;
			Iterator<Edge> adjIterator = adjacentCities.iterator();            
			while (adjIterator.hasNext()) {
				Edge e = adjIterator.next();
				g2.drawLine(e.x1, e.y1, e.x2, e.y2);
			}			
		}
		
		// Reinitialize iterator
		namesIterator = names.iterator();
		
		while (namesIterator.hasNext()){
			Vertex vertex = cities.get(namesIterator.next());
			String label = vertex.name;
			int x = vertex.x;
			int y = vertex.y;

			g2.setColor(Color.BLACK);
            g2.drawString(label, x+15, y+5);
			
			Color random = mixRandomColorWith(mint);
			g2.setColor(random);
			Ellipse2D.Double city = new Ellipse2D.Double(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
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
