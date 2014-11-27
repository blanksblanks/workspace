import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

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

	private static HuffmanNode[] buildTinyTrees(LinkedList<String> listOfLines) {
		Iterator<String> lines = listOfLines.iterator();
		try {
			// initializes original array with max capacity for ascii characters
			HuffmanNode[] original = new HuffmanNode[128];
			int characters = 0;
			while (lines.hasNext()) {
				String line = lines.next();
				for (int i = 0; i < line.length(); i++) {
					// extract ascii so characters are index in alphabetical
					// order
					int ascii = (int) line.charAt(i);
					if (original[ascii] == null) {
						// if first seeing char, cast char to string and create
						// tiny Huffman tree
						String character = Character.toString(line.charAt(i));
						original[ascii] = new HuffmanNode(character);
						characters++;
					} else
						// we've seen this char so increment its frequency
						original[ascii].increaseFrequency();
				}
			}
			// initialize tiny tree node array with capacity for actual number
			// of chars seen
			HuffmanNode[] tinyTrees = new HuffmanNode[characters];
			int incrementer = 0;
			for (int j = 0; j < original.length; j++) {
				if (original[j] != null) {
					// copy character from original array into next incremental
					// index of tiny array
					tinyTrees[incrementer] = original[j];
					incrementer++;
				}
			}
			return tinyTrees;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err
					.println("Your input file contains non-ASCII characters, which this program does not support. Please try again. Good bye!");
			System.exit(1);
			return null;
		}
	}

	public static HuffmanTree tree;

	// Tester method that takes an input file from the command line
	public static void main(String[] args) throws FileNotFoundException {

		if (args.length == 1) {
			LinkedList<String> input = FileLineParser(args[0]);
			HuffmanNode[] forest = buildTinyTrees(input);
			tree = new HuffmanTree(forest);
			System.out
					.println("TABLE OF ASCII CHARACTERS AND CORRESPONDING HUFFMAN CODES:");
			for (int i = 0; i < forest.length; i++)
				System.out.println(forest[i].toString() + " "
						+ forest[i].getBinaryCode());
			System.out.println("\nHEADER:");
			tree.getHeader();
		} else {
			System.err
					.println("A text input file needs to be specified. Please try again. Good bye!");
			System.exit(1);
		}

		/*
		 * GUI-RELATED METHODS
		 */

		// Creating the JFrame
		JFrame frame = new JFrame();
		frame.setTitle("Huffman Tree Encoding");
		frame.setPreferredSize(new Dimension (1280, 720));

		// Constants
		final int FIELD_WIDTH = 250;
		final int FIELD_HEIGHT = 80;
		final JTextArea textField = new JTextArea();
		final JTextArea binaryField = new JTextArea();
		final JTextArea resultField = new JTextArea();
		final JLabel resultLabel = new JLabel();

		// Set size
		textField.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		textField.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		binaryField.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		binaryField.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		resultField.setPreferredSize(new Dimension(FIELD_WIDTH*2, FIELD_HEIGHT));
		resultField.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		// Set height
		textField.setText("Enter some text here.");
		binaryField.setText("Enter some 1's and 0's here.");
		resultLabel.setText("Result: ");
		JButton encodeButton = new JButton("Encode");
		JButton decodeButton = new JButton("Decode");

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

		encodeButton.addActionListener(new ButtonListener(textField,
				resultField, tree));
		decodeButton.addActionListener(new ButtonListener(binaryField,
				resultField, tree));
		// int y = (int) decodeButton.getPreferredSize().getHeight();

		// Add all the components
		frame.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(tree);
		Panel p = new Panel();
		// p.setPreferredSize(new Dimension(tree.getWidth(), 80));
		p.setLayout(new FlowLayout());
		p.add(textField);
		p.add(encodeButton);
		p.add(binaryField);
		p.add(decodeButton);
		p.add(resultLabel);
		p.add(resultField);
		frame.add(p, BorderLayout.NORTH);
		frame.add(scrollPane, BorderLayout.CENTER);

		// scrollPane.getViewport().setViewPosition(new
		// Point(tree.getMidX(),0));
		// Rectangle bounds = scrollPane.getViewport().getViewRect();
		// JScrollBar horizontal = scrollPane.getHorizontalScrollBar();
		// horizontal.setValue( (horizontal.getMaximum() - bounds.width) / 2 );
		// System.out.println(tree.getMidX());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}

class ButtonListener implements ActionListener {
	private String s;
	private JTextArea in;
	private JTextArea out;
	private HuffmanTree tree;

	public ButtonListener(JTextArea inputField, JTextArea outputField,
			HuffmanTree hufftree) {
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