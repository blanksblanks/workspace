import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class UnsortedList {
	
	public UnsortedList(){
		
		try{	
			
			File inFile = new File("inputfile.txt");
			Scanner input = new Scanner(inFile);
			PrintWriter output = new PrintWriter("output.txt");	
			
			// Creates character array to produce the list of bigrams found in the input file
			String word;
			while(input.hasNext())
			{
				word = input.next();
			    char[] stringArray;
			    stringArray = word.toCharArray();
			    for(int i=0; i < (stringArray.length-1); i++){ // stops at the second last element of array
			    		word = Character.toString(stringArray[i]); // first character
			    		word = word + stringArray[i+1]; // first and second characters to make bigram
			    		output.println(word);
			    		word = "";
			    }

			}
			
			output.close();
			System.out.println("Made output.txt which has all the bigrams.");
			
		}
		
		catch(Exception e){
			System.out.println("Messed up.");
			System.out.println(e);
			System.exit(0);
		}
	}
}
