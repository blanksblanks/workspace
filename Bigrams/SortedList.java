/*****************************
	Sorted List Class
*****************************/

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;


public class SortedList {

	public SortedList() {
		//Scanner scan = new Scanner(System.in);
		try{
			//System.out.println("I'm being awesome right now.");

			new bigramlist();
			//String fileName = scan.next();
			//Loads the file you want to read
			
			File inFileTwo = new File("bigramlist.txt");
			Scanner inputBigram = new Scanner(inFileTwo);

			//PrintWriter outputbigram = new PrintWriter("bigramlist.txt");
			//while (input.hasNext()){
				//System.out.println(input.next());
			//}
			String one;
			String two;
			//Makes/loads the file you want to write in
			PrintWriter output = new PrintWriter("output2.txt");
			
			while (inputBigram.hasNext()){
				one = inputBigram.nextLine();
				//System.out.println(one);
				File inFile = new File("output.txt");
				Scanner input = new Scanner(inFile);
				
				while (input.hasNext()){
					two = input.nextLine();
					if (two.equalsIgnoreCase(one)){
						output.println(two);
					}
				}
			}
			
			output.close();
			System.out.println("Made output2.txt, which has all the bigrams sorted alphabetically.\n...");			
			
			//File inFileThree = new File("output2.txt");
			//Scanner inputTwo = new Scanner(inFileThree);
			//while (inputTwo.hasNext()){
			//System.out.println(inputTwo.next());}
		}
		catch(Exception e){
			System.out.println("Something messed up. Please try again.");
			System.out.println(e);
		}
	}

}
