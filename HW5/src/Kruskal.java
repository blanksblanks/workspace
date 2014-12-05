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
		System.out.println("\nCity Names:\n" + names.toString());
		System.out.println("\nCity XY Coordinates:\n" + xyCoordinates.toString());
	
		final Graph graph = new Graph(pairs, distances, names, xyCoordinates);
		graph.kruskal();
		graph.repaint();
		
		/* GUI METHODS
		 * 
		 */
		
		JFrame frame = new JFrame();
		frame.setTitle("Kruskal's Algorithm");
		frame.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT + 100));
//		
//		final JLabel instructionLabel = new JLabel();
//		final JLabel startLabel = new JLabel();
//		final JTextField startField = new JTextField(FIELD_WIDTH);
//		final JLabel endLabel = new JLabel();
//		final JTextField endField = new JTextField(FIELD_WIDTH);
//		final JLabel resultLabel = new JLabel();
//		final JTextField resultField = new JTextField(FIELD_WIDTH*2 + 8);
//		
//		instructionLabel.setForeground(Color.BLUE);
//		instructionLabel.setText("NOTE ON FORMAT: Please enter city names with proper capitalization. Spacing does NOT matter. You may use spaces or leave them out.");
//		startLabel.setText("Start: ");
//		startField.setText("Enter a city of origin");
//		endLabel.setText("  End: ");
//		endField.setText("Enter a destination city");
//		resultLabel.setText("Total Distance and Route: ");
//		resultField.setText(" ");
//		
//		JButton findButton = new JButton("Find Shortest Path!");
//		findButton.addActionListener(new ButtonListener(startField, endField,
//				resultField, graph));
//		
//		startField.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				startField.setText("");
//			}
//		});
//		
//		endField.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				endField.setText("");
//			}
//		});
		
		frame.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(graph);
//		Panel p = new Panel();
//		p.setLayout(new FlowLayout());
//		p.setPreferredSize(new Dimension(PANEL_WIDTH, 95));
//		p.add(startLabel);
//		p.add(startField);
//		p.add(endLabel);
//		p.add(endField);
//		p.add(findButton);
//		p.add(resultLabel);
//		p.add(resultField);
//		p.add(instructionLabel);
//		frame.add(p, BorderLayout.NORTH);
		frame.add(scrollPane, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		} catch (FileNotFoundException e) {
			System.err.println("File not found. Please try again!");
		}
		
	}

}
//
//class ButtonListener implements ActionListener {
//	private JTextField start;
//	private JTextField end;
//	private JTextField out;
//	private Graph graph;
//
//	public ButtonListener(JTextField inputField, JTextField inputField2, JTextField outputField,
//			Graph cities) {
//		start = inputField;
//		end = inputField2;
//		out = outputField;
//		graph = cities;
//	}
//
//	public void actionPerformed(ActionEvent ae) {
//		String origin = start.getText().replaceAll("\\s+","");
//		String destination = end.getText().replaceAll("\\s+","");
//		if (ae.getActionCommand().equals("Find Shortest Path!")) {
//			try {
//			String result = graph.dijkstra(origin, destination);
//			graph.repaint(); // repaint even for invalid input to remove green route
//			out.setText(result + "\t" + graph.route);
//			} catch (UnderflowException e) {
//				System.err.println("Underflow Exception. Try again");
//			}
//		} 
//	}
//}