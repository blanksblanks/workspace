/**
 * 
 * Problem 2
 * @author nb2406
 *
 * Implement a recursive binary search method. Create a class called Problem2 that
 * tests this method on several inputs of pre-sorted arrays.
 *
 */

import java.io.*;
import java.util.Scanner;

public class Problem2 {
	
    public static void main(String[] args) throws FileNotFoundException {
    	
		boolean again = true;
		while (again) {
			try {
				if (args.length > 0) {
				File inFile = new File(args[0]);
				Scanner input = new Scanner(inFile);
//				PrintWriter output = new PrintWriter("output.txt");
				
				int count = 0;
				int[] arr;
				int index = 0;
//				
				while (input.hasNextLine()) {
					input.next();
//					output.println(line);
					System.out.println("Length is " + count);
					count++;
				}
				input.close();
//				input.reset();
//
				Scanner input2 = new Scanner(inFile);

//				if (count > 0) {
//					File outFile = new File("output.txt");
//					Scanner outputter = new Scanner(outFile);
//
					 arr = new int[count];
					 System.out.println("Array size is " + arr.length);
//				}
////					 while (outputter.hasNextLine()) {
//					 for (int i = 0; i < count; i++) {
				while (input2.hasNextLine()){
							String s = input2.nextLine();
							int n = Integer.parseInt(s);
							arr[index] = n;
//						arr[index] = input.nextInt();
							System.out.println("Array" + index + " is " + arr[index]);
							index++;
						}
					 input.close();
			
				 System.out.println("Array size is " + arr.length);
				 for (int j = 0; j < 25; j++) {
					 System.out.println("Array" + j + " is " + arr[j]);
				 }
				again = false;
//				int[] arr1 = {2,3,23,234,567,876,900,976,999};
				
				Scanner readin = new Scanner(System.in);
				System.out.print("Please enter the number you're searching for: ");
				int searchTerm = readin.nextInt(); 

		        int i;
		        i = recursiveBinarySearch(arr,0,arr.length,searchTerm);
		        System.out.println("Found " + searchTerm + " at index: "+ i);
//		        i = recursiveBinarySearch(arr,0,arr.length,2);
//		        System.out.println("Found 2 at "+i+" index");
//		        i = recursiveBinarySearch(arr,0,arr.length,3);
//		        System.out.println("Found 3 at "+i+" index");
				}
	
			}// end try
			catch (IOException e) {
				System.out
						.println("Please try again with correct input file name");
				Scanner scan = new Scanner(System.in);
				args[0] = scan.next();
			} catch (ArrayIndexOutOfBoundsException e){
				System.out.println("Please enter at leat two command line arguments.");
			} catch (NullPointerException e) {
					System.out.println("There is nothing in this file.");
			}
			
			
		}// end while
    }
 
    public static int recursiveBinarySearch(int[] sortedArray, int start, int end, int target) {    
        if (start < end) {
            int midpoint = start + (end - start) / 2;  
            if (target < sortedArray[midpoint]) {
                return recursiveBinarySearch(sortedArray, start, midpoint, target);
            } else if (target > sortedArray[midpoint]) {
                return recursiveBinarySearch(sortedArray, midpoint + 1, end , target);
            } else {
                return midpoint;   
            }
        }
        return -(start + 1);
    }
}
 



//
//public class Problem2 {
//	
//	public static void main(String[] args) {
//		
//		if (args.length < 1) {
//			System.out.println("You need an input file. Try again!");
//		} else if (args.length > 1) {
//			System.out.println("You have too many arguments. Try again!");
//		} else {
//		    try {
//		    	int firstArg;
//		    	firstArg = Integer.parseInt(args[0]);
//		    } catch (FileNotFoundException e) {
//		        System.err.println("Argument" + args[0] + " must be a .txt file.");
//		        System.exit(1);
//		    }
//		    
//		Scanner readin = new Scanner(System.in);
//
//		System.out.print("Please enter a number: ");
//		int first = readin.nextInt(); 
//
//	}
//
//}


//TODO: Read command args so user can input a text file
//TODO: Add method to read text file to an array, assume sorted and newline for
//each int
//TODO: Ask if binary search will just be for ints?
//TODO: Add method for asking user input - what number are they looking for?

//TODO: Ask - why does recursion method cease to work when the array size is
//bigger than the number of items?
//TODO: is there a better way than creating a new file? why doens't reset input
//work? it's O(N) to count for array size and then copy each line to array
//TODO: note errors for future reference: remember to next line, print values
//after i=+;
//TODO: Clean up and comment code and find a version control system for commits
//TODO: ask how you can check if next user input is int or not
//TODO: ask if we have to handle errors

        
    	
