/**
 * 
 * Problem 2
 * 
 * @author nb2406
 * 
 *         Implement a recursive binary search method. Create a class called
 *         Problem2 that tests this method on several inputs of pre-sorted
 *         arrays.
 * 
 */

import java.io.*;
import java.util.Scanner;

public class Problem2 {

	public static void main(String[] args) throws IOException {

		if (args.length > 0) {
			
			String fileName = args[0];
			File inFile = new File(fileName);
			
			if (inFile.exists()) {		
				
				int count = 0;
				int index = 0;
				int[] arr;

				Scanner input = new Scanner(inFile);
				while (input.hasNextLine()) {
					input.nextLine();
					count++;
				}
				input.close();

				Scanner input2 = new Scanner(inFile);
				arr = new int[count];
				System.out.println("Array size is " + arr.length);
				while (input2.hasNextLine()) {
					String s = input2.nextLine();
					int n = Integer.parseInt(s);
					arr[index] = n;
					index++;
				}
				input2.close();

				Scanner readin = new Scanner(System.in);
				System.out.print("Please enter the search key: ");
				int searchTerm = readin.nextInt();
				System.out.println("You entered " + searchTerm);

				int i;
				i = recursiveBinarySearch(arr, 0, arr.length, searchTerm);
				if (i == -26) {
					System.out.println(searchTerm + " is not found.");
				} else {
					System.out.println(searchTerm + " is found at index " + i+1);
				}
			}
		}

	}

	// 
	public static int recursiveBinarySearch(int[] sortedArray, int start,
			int end, int target) {
		if (start < end) {
			int midpoint = start + (end - start) / 2;
			if (target < sortedArray[midpoint]) {
				return recursiveBinarySearch(sortedArray, start, midpoint,
						target);
			} else if (target > sortedArray[midpoint]) {
				return recursiveBinarySearch(sortedArray, midpoint + 1, end,
						target);
			} else {
				return midpoint;
			}
		}
		return -(start + 1);
	}
}