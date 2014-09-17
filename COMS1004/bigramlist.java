/*****************************
	Bigram List Class
*****************************/

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;


public class bigramlist {
	//public static void main(String[] args){
	public bigramlist(){
		try{
			PrintWriter output = new PrintWriter("bigramlist.txt");

			String word;
			char[] list = new char[26];
			list[0] = 'a';
			list[1] = 'b';
			list[2] = 'c';
			list[3] = 'd';
			list[4] = 'e';
			list[5] = 'f';
			list[6] = 'g';
			list[7] = 'h';
			list[8] = 'i';
			list[9] = 'j';
			list[10] = 'k';
			list[11] = 'l';
			list[12] = 'm';
			list[13] = 'n';
			list[14] = 'o';
			list[15] = 'p';
			list[16] = 'q';
			list[17] = 'r';
			list[18] = 's';
			list[19] = 't';
			list[20] = 'u';
			list[21] = 'v';
			list[22] = 'w';
			list[23] = 'x';
			list[24] = 'y';
			list[25] = 'z';
			
			for (int i=0; i<26; i++){
				for (int j=0; j<26; j++){
					word = Character.toString(list[i]);
					word = word + list[j];
			    	output.println(word);
			    	word = "";

				}
			    
				
			}
			output.close();
			
            // Prints out bigramlist.txt
            // File inFileTwo = new File("bigramlist.txt");
			// Scanner inputTwo = new Scanner(inFileTwo);
				// while (inputTwo.hasNext()){
				// System.out.println(inputTwo.next());
				// }
            
            }
        
		catch (Exception e){
			System.out.println("FAIL.");
		}
	}
}
