/**
 * 
 * Problem 3
 * 
 * @author nb2406
 * 
 *         Instructions: Implement a recursive binary search method. Create a
 *         class called Problem3 that takes in a .txt file with a pre-sorted
 *         array and prompts the user to enter a number to search for from
 *         stdin. Problem3 should print whether the number was found and, if the
 *         number was found, the index of the found element. Don't worry about
 *         multiple instances of an integer (just return one of the indexes).
 * 
 */

import java.io.*;
import java.util.Scanner;

public class Problem3 {

	public static void main(String[] args) throws IOException {

		if (args.length > 0) {

			// Assigns first argument to file name
			String fileName = args[0];
			File inFile = new File(fileName);

			// Checks if file exists before creating Scanner
			if (inFile.exists()) {

				int count = 0;
				int i = 0;
				int[] arr;

				// Scanner counts elements in file to determine array size
				Scanner input = new Scanner(inFile);
				while (input.hasNextLine()) {
					input.nextLine();
					count++;
				}
				input.close();

				// Creates new array based on number of numbers in file
				arr = new int[count];
				System.out.println("Array size is " + arr.length);

				// Creates new scanner to copy numbers from file to array from
				// the top
				Scanner input2 = new Scanner(inFile);
				while (input2.hasNextLine()) {
					String s = input2.nextLine();
					int n = Integer.parseInt(s);
					arr[i] = n;
					i++;
				}
				input2.close();

				// Asks user input for search key
				Scanner readin = new Scanner(System.in);
				System.out.print("Please enter the search key: ");
				int searchTerm = readin.nextInt();
				System.out.println("You entered " + searchTerm);

				// Searches for search key in pre-sorted array and returns index
				int index, invalid;
				index = recursiveBinarySearch(arr, 0, arr.length, searchTerm);
				invalid = (arr.length + 1) * -1;

				// Prints results out for users
				if (index == invalid) {
					System.out.println(searchTerm + " is not found");
				} else {
					System.out.println(searchTerm + " is found at index "
							+ index);
				}
			}
		}

	}

	// Recursive binary search method that returns user-defined target
	// from a pre-sorted array of integers
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