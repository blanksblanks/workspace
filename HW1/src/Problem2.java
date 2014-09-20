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

	public static void main(String[] args) throws FileNotFoundException {

		if (args.length > 0) {
			File inFile = new File(args[0]);
			Scanner input = new Scanner(inFile);

			int count = 0;
			int[] arr;
			int index = 0;

			while (input.hasNextLine()) {
				input.next();
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

			input.close();

			Scanner readin = new Scanner(System.in);
			System.out.print("Please enter the search key: ");
			int searchTerm = readin.nextInt();
			System.out.println("You entered " + searchTerm + ".");

			int i;
			i = recursiveBinarySearch(arr, 0, arr.length, searchTerm);
			if (i == -26) {
				System.out.println(searchTerm + " is not found.");
			} else {
				System.out
						.println(searchTerm + " is found at index " + i + ".");
			}
		}

	}

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

// TODO: Ask if binary search will just be for ints?
// TODO: Add method for asking user input - what number are they looking for?

// TODO: Ask - why does recursion method cease to work when the array size is
// bigger than the number of items?
// TODO: is there a better way than creating a new file? why doens't reset input
// work? it's O(N) to count for array size and then copy each line to array
// TODO: note errors for future reference: remember to next line, print values
// after i=+;
// TODO: Clean up and comment code and find a version control system for commits
// TODO: ask how you can check if next user input is int or not
// TODO: ask if we have to handle errors

