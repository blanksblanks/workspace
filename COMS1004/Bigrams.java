/*****************************
		Bigrams Class
*****************************/

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Bigrams {

	public Bigrams(){
		
		try{	
			//String bigramMatch = scan.next();
			File inFile = new File("output2.txt");
			Scanner input = new Scanner(inFile);
			//String word = "";
			PrintWriter output = new PrintWriter("output3.txt");			
			String largest = "";
			int largestNumber = 0;
			int currentNumber = 1;

			// for (int i=0; i>=10; i++) {
			String one = input.nextLine();
			while (input.hasNext()){
				String two = input.nextLine();
				if (two.equalsIgnoreCase(one)) {
					currentNumber++;
					if (currentNumber>=largestNumber){
					largestNumber = currentNumber;
					largest = one;
					}
				}
				if (!two.equalsIgnoreCase(one)){
					output.println(currentNumber + " " + one);
					currentNumber = 1;
					one = two;
				}
				//output.print("\n '" + largest + "' occurs " + largestNumber + " times.");
			}
		    
			// output.print("The most commonly occurring bigram is '" + largest + "', and it occurs " + largestNumber + " times in the text.");
			
			System.out.println("Made output3.txt, which has the top ten occurring bigrams.\n...");
			// System.out.println("...");
			output.close();
			// File inFileTwo = new File("output3.txt");
			// Scanner inputTwo = new Scanner(inFileTwo);
			//while (inputTwo.hasNext()){
			//	System.out.println(inputTwo.nextLine());
			// }
					
		}
		
		catch(Exception e){
			System.out.println("Messed up.");
		}
	}
}
