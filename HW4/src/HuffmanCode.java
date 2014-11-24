import java.io.File;
import java.io.FileNotFoundException;
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
			System.out.print("This file does not appear to exist. Please try again!");
			System.exit(1);
			return null;
		}
	}

	// TODO: Catch ArrayIndexOutOfBoundsException
	private static HuffmanNode[] buildTinyTrees(LinkedList<String> listOfLines) {
		Iterator<String> lines = listOfLines.iterator();
		// initializes original array with max capacity for ascii characters
		HuffmanNode[] original = new HuffmanNode[128];
		int characters = 0;
		while (lines.hasNext()) {
			String line = lines.next();
			for (int i = 0; i < line.length(); i++) {
				// extract ascii so characters are index in alphabetical order
				int ascii = (int) line.charAt(i);
				if (original[ascii] == null) {
					// if first seeing char, cast char to string and create tiny Huffman tree
					String character = Character.toString(line.charAt(i));
					original[ascii] = new HuffmanNode(character);
					characters++;
				} else
					// we've seen this char so increment its frequency
					original[ascii].increaseFrequency();
			}
		}
		// initialize tiny tree node array with capacity for actual number of chars seen
		HuffmanNode[] tinyTrees = new HuffmanNode[characters]; 
		int incrementer = 0;
		for (int j = 0; j < original.length; j++) {
			if (original[j] != null) {
				// copy character from original array into next incremental index of tiny array
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
		} else {
			System.out
					.println("One input file needs to be specified. Please try again!");
			System.exit(1);
		}

		// Creating the JFrame
		JFrame frame = new JFrame();
		frame.setTitle("Huffman Tree Encoding");
		// HuffmanTreeComponent mc = new HuffmanTreeComponent();

		// Constants
		final int FIELD_WIDTH = 20;
		final JTextField textField = new JTextField(FIELD_WIDTH);
		final JTextField binaryField = new JTextField(FIELD_WIDTH);
		final JTextField resultField = new JTextField(FIELD_WIDTH * 2);
		final JLabel resultLabel = new JLabel();
		textField.setText("Enter some text here.");
		binaryField.setText("Enter some 1's and 0's here.");
		resultLabel.setText("Result: ");
		JButton encodeButton = new JButton("Encode");
		JButton decodeButton = new JButton("Decode");
//		JButton leftButton = new JButton("Left");
//		JButton rightButton = new JButton("Right");

			
		textField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
			}
		});

		binaryField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent m) {
				binaryField.setText("");
			}
		});
		
		encodeButton.addActionListener(new ButtonListener(textField, resultField, tree));
		decodeButton.addActionListener(new ButtonListener(binaryField, resultField, tree));
		
		// Add all the components
		frame.setLayout(new FlowLayout());
		frame.add(textField);
		frame.add(encodeButton);
		frame.add(binaryField);
		frame.add(decodeButton);
		frame.add(resultLabel);
		frame.add(resultField);
		frame.add(tree);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}

class ButtonListener implements ActionListener {
	private String s;
	private JTextField in;
	private JTextField out;
	private HuffmanTree tree;
	
	public ButtonListener(JTextField inputField, JTextField outputField, HuffmanTree hufftree){
		in = inputField;
		out = outputField;
		tree = hufftree;
	}
	
	public void actionPerformed(ActionEvent ae) {
		s = in.getText();
		if (s == "")
			out.setText("You forgot to input a value!");
		if (ae.getActionCommand().equals("Encode")) {
			String result = tree.encode(s);
			out.setText(result);
		} else if (ae.getActionCommand().equals("Decode")) {
			String result = tree.decode(s);
			out.setText(result);
		}
	}
	
}