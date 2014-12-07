/*
 *  Read in the cities from the cityxy.txt city/coordinate file that you used in the
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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Kruskal {
	
	public static void main(String[] args) {
		
		final int PANEL_WIDTH = 950;
		final int PANEL_HEIGHT = 500;
		final int OFFSET = 20;
		
		if (args.length != 1) {
			System.err.println("You need only one argument, a text file containing a list of cities and their xy coordinates. Please try again.");
			System.exit(1);
		}

		try {
		Scanner cityXY = new Scanner(new File(args[0])); // cityxy.txt
		
		LinkedList<String> pairs = new LinkedList<String>();
		LinkedList<Double> distances = new LinkedList<Double>();
		LinkedList<String> names = new LinkedList<String>();
		LinkedList<Integer> xyCoordinates = new LinkedList<Integer>();
		
		pairs = null;
		distances = null;
		
		while(cityXY.hasNext()){
			String city = cityXY.next();
			names.add(city); // add city name
			xyCoordinates.add((int) (cityXY.nextInt()/3 + OFFSET)); // add x/4+20
			xyCoordinates.add((int) (PANEL_HEIGHT - cityXY.nextInt()/3 + OFFSET)); // add height-y/4+20
		}

		cityXY.close();
//		System.out.println("\nCity Names:\n" + names.toString());
//		System.out.println("\nCity XY Coordinates:\n" + xyCoordinates.toString());
	
		final Graph graph = new Graph(pairs, distances, names, xyCoordinates);
		graph.kruskal();
		graph.repaint();
		
		/* GUI METHODS
		 * 
		 */
		
		JFrame frame = new JFrame();
		frame.setTitle("Kruskal's Algorithm: Find Minimum Spanning Tree");
		frame.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT + 100));		
		frame.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(graph);
		frame.add(scrollPane, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		} catch (FileNotFoundException e) {
			System.err.println("File not found. Please try again!");
		}
		
	}

}