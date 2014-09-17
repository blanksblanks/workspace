/*****************************
	Most Common Class
*****************************/

import java.util.Scanner;
import java.io.*;

public class MostCommon 
{

public MostCommon(){
	
try{
	
	PrintWriter output = new PrintWriter("output4.txt");
	File inFile = new File("output3.txt");
	Scanner input = new Scanner(inFile);
	
	final int TEN = 10; // Number of elements is top ten
	int[] numberArray = new int[TEN]; // Number array of occurrences
	String[] bigramArray = new String[TEN]; // Bigram array of ten most common 
	int number = 0; // Number of occurrences
	String bigram = ""; // Bigram
	
	while (input.hasNext())
	{
		number = Integer.parseInt(input.next()); // Converts number string into int type
		bigram = input.next(); // Stores the bigram

		// Sorting algorithm using two arrays
		int sort = 0; // Starts from the beginning and stores value in temp position
		String bigramTemp = "";
		int numberTemp = 0;
		for (sort = 0; sort < TEN; sort++)
		{
			if (number >= numberArray[sort])
			{
				numberTemp = numberArray[sort];
				bigramTemp = bigramArray[sort];
				numberArray[sort] = number;
				bigramArray[sort] = bigram;
				break;
			}
		}

		sort++; // Next index
		String bigramStored = "";
		int numberStored = 0;
		for (; sort < TEN; sort++) {
			numberStored = numberArray[sort]; // Storing values
			bigramStored = bigramArray[sort];
			numberArray[sort] = numberTemp; // Replacing values
			bigramArray[sort] = bigramTemp;
			numberTemp = numberStored; // Storing values into temp
			bigramTemp = bigramStored;
		}
	
	}
	
		output.print("Occurrences: \t");
		for (int i = 0; i < TEN; i++) {
			output.print(numberArray[i] + "\t");
		}
			output.println();
			output.print("Bigrams: \t");
		for (int i = 0; i < TEN; i++) {
			output.print(bigramArray[i] + "\t");
		}
			output.println("");
	
	output.close();
	
	System.out.println("Made output4.txt, which has the ten most common bigrams " +
					   "and \nthe number of occurrences, in sorted order.");

	System.out.println("\nFINAL OUTPUT:\n");
	// Prints out the final output file
		File inFileTwo = new File("output4.txt");
		Scanner inputTwo = new Scanner(inFileTwo);
			while (inputTwo.hasNext()){
			System.out.println(inputTwo.nextLine());
			}

		System.out.println("");
		// System.out.println("\n*Above is a list of the ten most common bigrams" +
		// "viewed from left to right by number of occurrences.\n");	
		}

		catch(Exception e){
				System.out.println("Messed up.");
				System.out.println(e);
				System.exit(0);
		}
	}
}
