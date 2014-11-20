import java.io.File;
import java.io.FileNotFoundException;
//import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

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
	
	// Accept input file from user and return a linked list of every line in the file
	private static LinkedList<String> FileLineParser(String fileName) throws FileNotFoundException {
		File inFile = new File(fileName);
		if (inFile.exists()) {
			LinkedList<String> ListOfLines = new LinkedList<>();
			Scanner input = new Scanner(inFile);
			while (input.hasNextLine()) {
				String line = input.nextLine() + "\n"; //adds new line char
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
	
	private static HuffmanNode[] forestOfTinyHuffmanTrees(LinkedList<String> input){
		Iterator<String> lines = input.iterator();
        HuffmanNode[] bigForest = new HuffmanNode[128];
		int counter = 0;
		while (lines.hasNext()) {
			String line = lines.next();
            for (int i = 0; i < line.length(); i++) {
                int ascii = (int) line.charAt(i); // extracts ascii representation of this char
                if (bigForest[ascii] == null) { // first time seeing this char
                    String character = Character.toString(line.charAt(i));
                    bigForest[ascii] = new HuffmanNode(character);
                    counter += 1;
                } else // seen this char so increment its frequency
                    bigForest[ascii].incrementFrequency();
            }	
		}
        HuffmanNode[] babyForest = new HuffmanNode[counter];
        int j = 0;
        for (int k = 0; k < bigForest.length; k++) {
            if (bigForest[k] != null) {
                babyForest[j] = bigForest[k];
//                System.out.println("Inserted " + bigForest[k] + "into index " + j);
                j++;
            }
        }
        return babyForest;
	}
	
    // Tester method that takes an input file from the command line
	public static void main(String[] args) throws FileNotFoundException {
		
		if (args.length == 1) {
			LinkedList<String> input = FileLineParser(args[0]);
			HuffmanNode[] forest = forestOfTinyHuffmanTrees(input);
			HuffmanTree tree = new HuffmanTree(forest);
	        for (int i = 0; i < forest.length; i++)
	            System.out.println(forest[i].toString() + "  frequency: " + forest[i].getFrequency());
//	        tree.printTree();
	        System.out.println("0110110010");
	        System.out.println(tree.decode("0110110010"));
		} else {
				System.out.println("One input file needs to be specified. Please try again!");
				System.exit(1);
			}
		
		// Creating the JFrame
		JFrame frame = new JFrame();
		HuffmanTreeComponent mc = new HuffmanTreeComponent();
		
		//Constants
		final int FIELD_WIDTH = 20;
		final JTextField textField = new JTextField(FIELD_WIDTH);
		final JTextField binaryField = new JTextField(FIELD_WIDTH);
		final JLabel binaryLabel = new JLabel();
		final JLabel textLabel = new JLabel();
		textField.setText("Enter some text");
		binaryField.setText("Enter some 1's and 0's");
		
		JButton encodeButton = new JButton("Encode");
		JButton decodeButton = new JButton("Decode");
//		encodeButton.addActionListener(new HuffmanListener(""))
	
		// Add all the components
		frame.setLayout(new FlowLayout());
		frame.add(textField);
		frame.add(encodeButton);
		frame.add(binaryLabel);
		frame.add(binaryField);
		frame.add(decodeButton);
		frame.add(textLabel);
		frame.add(mc);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}


}
