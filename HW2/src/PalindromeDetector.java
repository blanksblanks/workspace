import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 * Palindrome Detector
 * 
 * @author nb2406
 * 
 *         Palindrome Detector: A palindrome is a phrase that reads the same
 *         forwards as it does backwards. For example,
 *         "a man, a plan, a canal, Panama." is a palindrome. Ignore white space
 *         and punctuation. Write a program that uses a stack to check for
 *         palindromes in each line of a text file. Try your program on the
 *         example text file palindromes.txt. Your program should output the
 *         palindromes that it finds in the document. For example: java
 *         FindPalindromes palindromes.txt "a man, a plan, a canal, Panama" is a
 *         palindrome. "Don't nod" is a palindrome. "Taco Cat!" is a palindrome.
 *         You must write your own MyStack class for this problem. Don.t use the
 *         built in Stack. Feel free to use either a LinkedList, ArrayList or an
 *         array to implement the Stack.
 * 
 *         Clarification: You must be able to account for at least the
 *         punctuation contained in the palindromes.txt file provided by
 *         Professor Blaer.
 * 
 *         Game Plan:
 *         - Read each letter in the phrase. En-queue the letter into the queue, 
 *         and push the letter onto the stack
 *         - After we have read all of the letters in the phrase
 *         - Until the stack is empty, dequeue a letter from the queue and pop a
 *         letter from the stack.
 *         - If the letters are not the same, the phrase is not a palindrome
 * 
 */

public class PalindromeDetector {

	public boolean isPalindrome(String s) {
		// Regex to remove all non-words from text before evaluating it
		String textOnly = s.replaceAll("\\W", "").toLowerCase();
	    MyStack<String> palindromeStack = new MyStack<String>();
	    int depth = 0;
	    int start;
		
	    // Push each letter in the String into the stack
	    for (int i = 0; i < textOnly.length()/2; i++) {
	    	String letter = String.valueOf(textOnly.charAt(i));
	    	palindromeStack.push(letter);
	    	depth++;
	    }
	    		    	    	    
	    // Set start index to depth for even length strings, +1 for odd numbers
	    if (textOnly.length() %2 == 0) {
	    	start = depth;
	    } else {
	    	start = depth + 1;
	    }
	    
//    	System.out.println(textOnly + ": text " + depth  + " depth " + start + " start");
	    
	    // Compare letters in original order to reverse order with peek and pop
	    // Return false if there is a mismatch
	    for (int i = start; i < textOnly.length(); i++) {
        	String original = String.valueOf(textOnly.charAt(i));
        	String reverse = palindromeStack.peekAndPop();
//        	System.out.println("Original " + original + " reverse " + reverse);

        	if (reverse.compareTo(original) != 0) {
        		return false;
        	}
        }

	    return true;
	}
	
	// Parse each line from file and add as next element in a Linked List
	public static LinkedList<String> lineParser(String inputFile) {
		LinkedList<String> linesInFile = new LinkedList<String>();
		try {
			Scanner input = new Scanner(new File(inputFile));
			while (input.hasNextLine()) {
				String newLine = input.nextLine();
				linesInFile.add(newLine);
			}
			input.close();
		} catch (IOException e) {
			System.out.println("File was not found!");
		}
		return linesInFile;
	}
	
	// Tester method for Palindrome Detector
	public static void main(String[] args) {
		
		LinkedList<String> text = lineParser("palindromes.txt");
		PalindromeDetector detectPal = new PalindromeDetector();

		// Check each line element in text LL to see if any are palindromes
		for (int i = 0; i < text.size(); i++) {
			String line = text.get(i);
			if (detectPal.isPalindrome(line)) {
				System.out.println("Found a palindrome! \t\"" + line + "\"");
			} else {
				System.out.println("NOT a palindrome... \t\"" + line + "\"");
			}
		}
	}

}
