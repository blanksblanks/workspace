import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * Exercise 5.21
 * 
 * @author nb2406
 * 
 *         Weiss, Exercise 5.21: Implement a spelling checker by using a hash
 *         table. Assume that the dictionary comes from two sources: an existing
 *         large dictionary and a second file containing a personal dictionary.
 *         Output all misspelled words and the line numbers in which they occur.
 *         Also, for each misspelled word, list any words in the dictionary that
 *         are obtainable by applying any of the following rules: a. Add one
 *         character. b. Remove one character. c. Exchange adjacent characters.
 * 
 *         This is a command line application. The dictionary files should be
 *         provided as command line arguments to the programming. Here is a
 *         sample dictionary file. You do not have to submit the big dictionary
 *         file with your program, but you should submit a sample small
 *         dictionary file. Write your own hash function. In addition, the file
 *         being spellchecked should be provided as a command line argument.
 * 
 *         Pseudo code: hash "apple" key and point to []/LL/tree of possible misspellings
 *         add one char:
 *         string s = apple;
 *         while (apple has next)/for char in s{
 *         string t = s(remove i)
 *         if (s = hash)
 *          	insert t;
 *         i++;
 *         }
 *         
 *         exchange chars: (temp = next; next = current next; previous = current; current = temp)
 *         temp = i;
 *         i = i.next;
 *         i.next = temp;
 * 
 */

public class SpellChecker {
	
	// Accept dictionary file from user and return a hash table of all the strings
	// Assume each line contains only one dictionary entry/word
	private static QuadraticProbingHashTable<String> DictionaryHasher(String fileName) throws FileNotFoundException{
		QuadraticProbingHashTable<String> dictionary = new QuadraticProbingHashTable<>( );
		File inFile = new File(fileName);
		if (inFile.exists()) {
			Scanner input = new Scanner(inFile);
			while (input.hasNextLine()) {
				String entry = input.nextLine();
				dictionary.insert(entry);
			}
			input.close();
			return dictionary;
		} else {
				System.out
						.print("That file does not appear to exist. Please try again!");
				System.exit(1);
				return null;
			}
	}
	
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
	
	// Check linked list of strings and identify any words that are not in one of the two dictionaries
	// Call method that suggests corrections for misspelled words
	private static void spellChecker(LinkedList<String> input) {
		int lineNumber = 1;
		Iterator<String> lines = input.iterator();
		// Insert words and line number from each line into the tree
		while (lines.hasNext()) {
			String line = lines.next();
			StringTokenizer words = new StringTokenizer(line);
			while (words.hasMoreTokens()) {
				String word = words.nextToken();
				// Check that token contains alphabetical characters i.e. a word
				if (word.matches(".*[a-zA-Z]+.*")) {
					// Remove punctuation before and after each word (but not
					// inside, or single quotes)
					word = word.replaceFirst("^[^a-zA-Z']+", "")
							.replaceAll("[^a-zA-Z']+$", "").toLowerCase();
					if (!dict1.contains(word) && !dict2.contains(word)){
						System.out.print(word + ", line " + lineNumber);
						LinkedList<String> suggestions = wordSuggester(word);
						if (suggestions.size() == 0)
							System.out.println(", no suggestions");
						else {
							System.out.print(", suggestions: ");
							Iterator<String> suggestionsIterator = suggestions
									.iterator();
							while (suggestionsIterator.hasNext()) {
								System.out.print(suggestionsIterator.next());
								if (suggestionsIterator.hasNext())
									System.out.print(", ");
								else
									System.out.println();
							}
						}
						
					}
				}
			}
			lineNumber++;
		}
	}
	
	// Suggest spelling corrections for a misspelled word based on three criteria:
	private static LinkedList<String> wordSuggester(String word){
		char[] wordCharArr = word.toCharArray();
	    LinkedList<String> words = new LinkedList<String>();
	    // 1) Add one character
	    for (int i = 0; i <= wordCharArr.length; i++) {
        	String addedApostrophe = word.substring(0,i) + "'" + word.substring(i,wordCharArr.length);
	        for (char j = 'a'; j <= 'z'; j++) {
	        	String addedChar = word.substring(0,i) + j + word.substring(i,wordCharArr.length);
	        	if (dict1.contains(addedApostrophe) || dict2.contains(addedApostrophe))
	        		words.add(addedApostrophe);
	        	if (dict1.contains(addedChar) || dict2.contains(addedChar))
	        		words.add(addedChar);
	        }
	    }
	    
	    // 2) Remove one character
	    for (int i = 0; i <= wordCharArr.length - 1; i++) {
        	String removedChar = word.substring(0,i) + word.substring(i+1);
	        	if (dict1.contains(removedChar) || dict2.contains(removedChar))
	        		if (!words.contains(removedChar))
	        			words.add(removedChar);
	    }
	    

	 // 3) Swap adjacent characters
		for (int i = 0; i < wordCharArr.length - 1; i++) {
			char[] c = word.toCharArray();
			char temp = c[i];
			c[i] = c[i + 1];
			c[i + 1] = temp;
			String swapped = new String(c);
        	if (dict1.contains(swapped) || dict2.contains(swapped))
        		if (!words.contains(swapped))
        			words.add(swapped);
		}
		
		return words;
	}
	
    private static QuadraticProbingHashTable<String> dict1;
    private static QuadraticProbingHashTable<String> dict2;

    // Tester method that takes two dictionary files and an input file from the command line
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length == 3) {
			dict1 = DictionaryHasher(args[0]);
			dict2 = DictionaryHasher(args[1]);
			LinkedList<String> inputFile = FileLineParser(args[2]);
			spellChecker(inputFile);
		} else {
				System.out.println("Two dictionaries and one input file need to be specified. Please try again!");
				System.exit(1);
			}
	}
	
	
}
