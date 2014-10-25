/**
 * 
 * Word Lines
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class WordLines {

	public static void main(String[] args) throws FileNotFoundException {

	    if (args.length < 1 || args.length > 1) {
	        System.out.println("Input file not properly specified. Please try again!");
		    System.exit(1);
		} else {
			File inFile = new File(args[0]);
			if (inFile.exists()) {
				LinkedList<String>linesList = new LinkedList<>();
				Scanner input = new Scanner(inFile);
				while (input.hasNextLine()) {
					String line = input.nextLine();
					linesList.add(line);
				}
				input.close();
				AvlTree<String> textTree = buildTree(linesList);
				textTree.printTree();	
			} else {
				System.out.print("That file does not appear to exist. Please try again!");
				System.exit(1);
			}
		}
	}
	
	public static AvlTree<String> buildTree(LinkedList<String> LoL){
		AvlTree<String> wordTree = new AvlTree<>();
		
		for (int i=0; i<LoL.size(); i++){
    		String newLine = LoL.get(i);
    		
//    		turn string into an iterator of words
    		StringTokenizer st = new StringTokenizer(newLine);

    	    while (st.hasMoreTokens()) {	 
    	    	String newWord = st.nextToken();
    	    	 
//    	    	remove punctuation before and after each word (but not inside, or single quotes)
    	    	newWord = newWord.replaceFirst("^[^a-zA-Z']+", "")
    	    	    .replaceAll("[^a-zA-Z']+$", "").toLowerCase();	
    	    	 
//    	    	System.out.println("added "+newWord+", on line "+(i+1));
    	    	wordTree.insert(newWord, i+1);    	 
    	    }
        }
		return wordTree;	
	}

}
