/**
 * The file citypairs.dat contains selected city pairs and their distances. Each
 * line of the file contains 2 city names and a distance. Assume that these
 * links are bi-directional, if you can from NewYork to Boston, you can get from
 * Boston to NewYork. Write a program to calculate the shortest path between any
 * given city pair. Your program should do the following:
 * 
 * Read in the city pairs and distances. Implement Dijkstra's algorithm using as
 * the edge cost the given city pair's distance. You should use the priority
 * queue implementation of Dijkstra's algorithm. Input should be via a Java GUI
 * window that asks for the source and destination cities. Map the cities onto
 * the window and draw the shortest path in the window between those cities. A
 * list of (X,Y) coordinates for each of the cities can be found in the file
 * cityxy.txt. Display in the window the path length of the shortest path.
 * 
 * Read in the cities from the cityxy.txt city/coordinate file that you used in the
 * previous problem. Assume that the cities in the graph are fully connected -
 * an edge exists between every city pair. Use the 2-D Euclidean distance
 * between two cities as the edge cost. Using these edge costs (the path
 * lengths), implement Kruskal's algorithm for finding the minimum spanning tree
 * of the city graph. Output the edge pairs that make up the minimum spanning
 * tree to the console. Create a GUI window and draw a map of the cities,
 * include the edges that are part of the MST that you calculated by Kruskal's
 * algorithm.
 * 
 * 
 * @author nb2406
 * 
 */

import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DijkstraAlgorithm {
	
	public static void main(String[] args) {
		
		try {
		Scanner cityPairs = new Scanner(new File(args[0])); // citypairs.dat
		Scanner cityXY = new Scanner(new File(args[1])); // cityxy.txt
		
		LinkedList<String> pairs = new LinkedList<String>();
		LinkedList<Double> distances = new LinkedList<Double>();
		LinkedList<String> names = new LinkedList<String>();
		LinkedList<Double> xyCoordinates = new LinkedList<Double>();

		
		while (cityPairs.hasNext()){
			pairs.add(cityPairs.next()); // add v1
			pairs.add(cityPairs.next()); // add v2
			distances.add(cityPairs.nextDouble()); // add edge
		}
		
		while(cityXY.hasNext()){
			String city = cityXY.next();
			if (pairs.contains(city)){
				names.add(city); // add city name
				xyCoordinates.add(cityXY.nextDouble()); // add x
				xyCoordinates.add(cityXY.nextDouble()); // add y
			}
		}

		cityPairs.close();
		cityXY.close();
		
		System.out.println("\nCity Pairs:\n" + pairs.toString());
		System.out.println("\nCity Distances:\n" + distances.toString());
		System.out.println("\nCity Names:\n" + names.toString());
		System.out.println("\nCity XY Coordinates:\n" + xyCoordinates.toString());
	
//		final Graph graph = new Graph(pairs, distances, names, xyCoordinates);
		
		
		} catch (FileNotFoundException e) {
			System.err.println("File not found. Please try again!");
		}
		
		
	}

}
