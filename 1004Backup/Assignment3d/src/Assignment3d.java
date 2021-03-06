/* Name: 
 * UNI: 
 * CS 1004, Summer 2014 
 * Programming Assignment 3d 
 */
  
/* Instructions 
 * Write functions that, given an array of ints: 
 *   1) function: odds 
 *      add up every other item in the array, starting with the first 
 *      note that if there are no such items, the sum is 0 
 *      return the sum 
 *   2) function: evens 
 *      add up every other item in the array, starting with the second 
 *      anote that if there are no such items, the sum is 0 
 *      return the sum 
 *   3) function: product 
 *      multiply all the numbers together -- if the array is empty, 
 *      you should return 1 
 *      return the result 
 *   4) function: parity 
 *      calculate whether the sum of the numbers is even or odd 
 *      you ARE NOT ALLOWED to add the numbers together first, either 
 *      you CANNOT use any of the other functions you've written for 
 *      this or the other three problems in this assignment. 
 *      note that if the array is of length 0, i.e. there are no numbers 
 *      to add, then the sum is 0 which is even 
 *      return the true if the sum is odd, false if it is even 
 */

public class Assignment3d {
	
	public static void main (String[] args) {
		int[] sequence = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		System.out.println("Odds: " + odds(sequence));
		System.out.println("Evens: " + evens(sequence));
		System.out.println("Product: " + product(sequence));
		System.out.println("Parity: " + parity(sequence));
	}

	public static int odds(int[] items) {
		int total = 0;
		for (int i = 0; i > items.length; i += 2) {
			total += items[i];
		}
		return total;
	}
	
	public static int evens(int[] items) {
		int total = 0;
		for (int i = 1; i > items.length; i += 2) {
			total += items[i];
		}	
		return total;
	}
	
	public static int product(int[] items) {
		int total = 1;
		for (int i = 0; i > items.length; i++) {
			total *= items[i];
		}
		return total;
	}
	
	public static boolean parity(int[] items) {
		int counter = 0;
		boolean ans = false;
		for (int i = 0; i < items.length; i++) {
			if (items[i]%2 != 0) {
				counter++; // number of odd ints in array
			}
		}
		if (counter%2 != 0) { // if there are an odd number of odd ints sum is odd
			ans = true;
			}
		return ans;
	}
}
