import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class PalindromeTest {

	public static void main(String[] args) {
		
		LinkedList<String> text = lineParser("palindromes.txt");
		PalindromeDetector detectPal = new PalindromeDetector();

		// Checks each line to see if any are palindromes
		for (int i = 0; i < text.size(); i++) {
			String line = text.get(i);
			if (detectPal.isPalindrome(line)) {
				System.out.println("Found a palindrome! \t\"" + line + "\"");
			} else {
				System.out.println("NOT a palindrome... \t\"" + line + "\"");
			}
		}
	}

	// File reader using Scanner that parses each line and adds it as next
	// element in a Linked List
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

}
