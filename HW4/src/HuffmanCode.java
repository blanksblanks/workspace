import java.io.File;
import java.io.FileNotFoundException;
// import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/**
 * 
 * HuffmanCode
 * 
 * @author blanks
 * 
 *         1. Write a program to create a Huffman code for a message. The
 *         program should to the following:
 * 
 *         o The program should take as a command line argument the name of a
 *         file which contains some text. It should then compute the frequencies
 *         of the characters in that text and internally build the huffman tree.
 *         Use a modified version of Weiss' binary heap implementation for the
 *         priority queue, do not use the built in java version. You should then
 *         print out in the console window a table of characters along with the
 *         corresponding huffman codes.
 * 
 *         o Next, the program should display the tree. Use a GUI window to
 *         display the tree. Underneath each leaf node display the huffman code
 *         (as 0's and 1's) for that particular leaf. You can use 0 for the left
 *         child and 1 for the right child (what ever you do, just be
 *         consistent).
 * 
 *         o In addition to displaying the tree, the GUI should have a text
 *         field for the code of 0's and 1's. When you click a button, the
 *         program should decode your input based on the huffman tree that you
 *         constructed from the original input file. If there is an error in the
 *         code, print that an error occurred, rather than the decoded message.
 * 
 *         o Finally, the GUI should have a text field for the user to enter a
 *         series of characters. When the user presses a button, those
 *         characters should be converted into the corresponding huffman code
 *         based on the huffman tree built from the original file input.
 * 
 */

public class HuffmanCode {

	// Accept input file from user and return a linked list of every line in the
	// file
	private static LinkedList<String> FileLineParser(String fileName)
			throws FileNotFoundException {
		File inFile = new File(fileName);
		if (inFile.exists()) {
			LinkedList<String> ListOfLines = new LinkedList<>();
			Scanner input = new Scanner(inFile);
			while (input.hasNextLine()) {
				String line = input.nextLine() + "\n"; // adds new line char
				ListOfLines.add(line);
			}
			input.close();
			return ListOfLines;
		} else {
			System.out
					.print("This file does not appear to exist. Please try again!");
			System.exit(1);
			return null;
		}
	}

	private static HuffmanNode[] buildTinyTrees(LinkedList<String> listOfLines) { // of
																					// tiny
																					// Huffman
																					// trees!
		Iterator<String> lines = listOfLines.iterator();
		HuffmanNode[] original = new HuffmanNode[128]; // initializes array with
														// capacity for max
														// number of ascii
														// characters
		int characters = 0;
		while (lines.hasNext()) {
			String line = lines.next();
			for (int i = 0; i < line.length(); i++) {
				int ascii = (int) line.charAt(i); // extracts ascii so
													// characters are indexed in
													// alphabetical order
				if (original[ascii] == null) { // this is the first time seeing
												// this char
					String character = Character.toString(line.charAt(i)); // casts
																			// the
																			// character
																			// as
																			// String
					original[ascii] = new HuffmanNode(character); // creates a
																	// tiny
																	// Huffman
																	// tree
					characters++;
				} else
					// seen this char so increment its frequency
					original[ascii].increaseFrequency();
			}
		}
		HuffmanNode[] tinyTrees = new HuffmanNode[characters]; // initialize
																// tiny tree
																// array with
																// capacity for
																// actual number
																// of characters
																// seen
		int incrementer = 0;
		for (int j = 0; j < original.length; j++) {
			if (original[j] != null) { // copy character from original array
										// into next incremental index of tiny
										// tree array
				tinyTrees[incrementer] = original[j];
				incrementer++;
			}
		}
		return tinyTrees;
	}

	public static HuffmanTree tree;

	// Tester method that takes an input file from the command line
	public static void main(String[] args) throws FileNotFoundException {

		if (args.length == 1) {
			LinkedList<String> input = FileLineParser(args[0]);
			HuffmanNode[] forest = buildTinyTrees(input);
			tree = new HuffmanTree(forest);
			for (int i = 0; i < forest.length; i++)
				System.out.println(forest[i].toString() + " "
						+ forest[i].getBinaryCode());
			tree.printTree();
			// System.out.println("0110110010 = " + tree.decode("0110110010"));
			// System.out.println("01101100101 = " +
			// tree.decode("01101100101"));
			// System.out.println("eats = " + tree.encode("east"));
			// System.out.println("good eats = " + tree.encode("good eats"));
		} else {
			System.out
					.println("One input file needs to be specified. Please try again!");
			System.exit(1);
		}

		// Creating the JFrame
		JFrame frame = new JFrame();
		// HuffmanTreeComponent mc = new HuffmanTreeComponent();

		// Constants
		final int FIELD_WIDTH = 20;
		final JTextField textField = new JTextField(FIELD_WIDTH);
		final JTextField binaryField = new JTextField(FIELD_WIDTH);
		final JLabel binaryLabel = new JLabel();
		final JLabel textLabel = new JLabel();
		textField.setText("");
		binaryLabel.setText("<- Enter some text here.");
		binaryField.setText("");
		textLabel.setText("<- Enter some 1's and 0's here.");

		JButton encodeButton = new JButton("Encode");
		JButton decodeButton = new JButton("Decode");

		// textField.addMouseListener(new MouseAdapter(){
		// public void mouseClicked(MouseEvent e){
		// textField.setText("");
		// }
		// });
		//
		// binaryField.addMouseListener(new MouseAdapter(){
		// public void mouseClicked(MouseEvent m){
		// binaryField.setText("");
		// }
		// });

		encodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (ae.getActionCommand().equals("Encode")) {
					// System.out.println("Encode got pressed");
					String in = textField.getText();
					if (in == "") binaryLabel.setText("You forgot to input a value!");
					else {
						String out = tree.encode(in);
						// textField.setText(out);
						binaryLabel.setText(out);
					}
				}
			}
		});

		decodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (ae.getActionCommand().equals("Decode")) {
					// System.out.println("Decode got pressed");
					String in = binaryField.getText();
					if (in == "") textLabel.setText("You forgot to input a value!");
					else {
						String out = tree.decode(in);
						// binaryField.setText(out);
						textLabel.setText(out);
					}
				}
			}
		});

		// Add all the components
		frame.setLayout(new FlowLayout());
		frame.add(textField);
		frame.add(binaryLabel);
		frame.add(encodeButton);

		frame.add(binaryField);
		frame.add(textLabel);
		frame.add(decodeButton);

		frame.add(tree);
		// frame.add(mc);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
