/*****************************
	Bigram Frequency Test
	By: Nina Baculinao
	nb2406
*****************************/

import java.io.*;
import java.util.Scanner;


public class Test {
	
	public static void main(String[] args) throws Exception
	{
	
		Scanner scan = new Scanner(System.in);
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1);
		//System.out.println(System.getProperty("user.dir"));

		try{
			System.out.println("Hi, welcome to the Bigram Frequency Test!");
			System.out.println("Bigrams are 2-letter sequences, like: hi!" );
			System.out.println("I can tell you the 10 most common bigrams in your file.");
			System.out.println("So what file are we dealing with today?");

			String fileName = scan.next();
			//Loads the file you want to read, called "input.txt"
			File inFile = new File(fileName);
			Scanner input = new Scanner(inFile);

			String word = "";
			//Makes/loads the file you want to write in
			PrintWriter output = new PrintWriter("output.txt");
			//Creates array of the list of bigrams
			while(input.hasNext()){
				word = input.next();
			    char[] stringArray;
			    stringArray = word.toCharArray(); 
			    for(int i = 0; i < (stringArray.length-1); i++){
			    		word = Character.toString(stringArray[i]);
			    		word = word + stringArray[i+1]; 
			    		output.println(word);
			    		word = "";
			    }

			}
			System.out.println("...\nMade output.txt, which has all the bigrams in your file.\n...");
			output.close();
	//		File inFileTwo = new File("output.txt");
		//	Scanner inputTwo = new Scanner(inFileTwo);
			//while (inputTwo.hasNext()){
			//System.out.println(inputTwo.next());}
			new SortedList();
			new Bigrams();
			new MostCommon();
		}
		catch(Exception e){
			System.out.println("Something messed up.  Please try again.");
			System.out.println(e);
		}


	}

}
