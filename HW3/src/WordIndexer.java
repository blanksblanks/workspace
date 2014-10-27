import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Iterator;

/**
 * 
 * WordIndexer
 * 
 * @author nb2406
 * 
 *         Write a command line application that indexes the words contained in
 *         a text file (provided to the program as a command line argument).
 *         Your program should go through the input file line by line. For each
 *         line, extract each word, and insert that word, along with it's
 *         location into an AVL tree. Each element of the AVL tree should
 *         contain a unique word and a linked list of line numbers where that
 *         word occurs. If word already exists in the AVL Tree, simply add the
 *         new line number to the existing node. When you have finished, print
 *         out each unique word that appeared in the input file along with a
 *         list of line on which that word appears. You may use Weiss' AVL tree
 *         code as a starting point for your program.
 * 
 */

public class WordIndexer {
	
	// Accept input file from user and return a linked list of every line in the file
	private static LinkedList<String> FileLineParser(String fileName) throws FileNotFoundException {
		File inFile = new File(fileName);
		if (inFile.exists()) {
			LinkedList<String> linesList = new LinkedList<>();
			Scanner input = new Scanner(inFile);
			while (input.hasNextLine()) {
				String line = input.nextLine();
				linesList.add(line);
			}
			input.close();
			return linesList;
		} else {
				System.out
						.print("That file does not appear to exist. Please try again!");
				System.exit(1);
				return null;
			}
	}
	
	// TODO: Should this method be private and initialized in the wordTree constructor?
	
	// Build tree containing words and line numbers from a linked list
	private static AvlWordTree<String> indexWords(LinkedList<String> ListOfLines) {
		// Initialize tree, line number and iterator for each line
		AvlWordTree<String> wordTree = new AvlWordTree<>();
		int lineNumber = 1;
		Iterator<String> lines = ListOfLines.iterator();
		// Insert words and line number from each line into the tree
		while (lines.hasNext()) {
			String line = lines.next();
			// Delimit words by spaces
			StringTokenizer words = new StringTokenizer(line);
			while (words.hasMoreTokens()) {
				String word = words.nextToken();
				// Check that token contains alphabetical characters i.e. is a word
				if (word.matches(".*[a-zA-Z]+.*")) {
					// Remove punctuation (except for apostrophes) that come before or after each word
					// (keep punctuation in the middle of alphabetical characters)
					word = word.replaceFirst("^[^a-zA-Z']+", "")
							.replaceAll("[^a-zA-Z']+$", "").toLowerCase();
					wordTree.insert(word, lineNumber);
				}
			}
			lineNumber++;
		}
		return wordTree;
	}
	
	// Test program
	public static void main(String[] args) throws FileNotFoundException  {
	if (args.length == 1) {
			LinkedList<String> linesList = FileLineParser(args[0]);
			AvlWordTree<String> wordTree = indexWords(linesList);
			wordTree.printTree();
		} else {
			System.out.println("Input file not properly specified. Please try again!");
			System.exit(1);
		}
	}

}
