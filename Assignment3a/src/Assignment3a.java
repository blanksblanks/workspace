/* Name: 
 * UNI: 
 * CS 1004, Summer 2014 
 * Programming Assignment 3a 
 */
  
/* Instructions 
 * In this assignment you'll create some simple functions. 
 *   1) function: divides 
 *      Make a function that accepts two ints and then 
 *      returns true if the first divides the second and 
 *      returns false otherwise. 
 *   2) function: abs 
 *      Make a function that returns the absolute value of 
 *      an int -- i.e. -x if x < 0, and x otherwise 
 *   3) function: max 
 *      Make a function that returns the max of 2 ints 
 *   4) function: maxMagnitude 
 *      Make a function that takes two ints and returns 
 *      the int with the maximum absolute value 
 *   5) function: factor 
 *      Make a function that returns the first positive 
 *      factor of an int greater than 1. For 0 it should 
 *      return 0, for +/-1 it should return 1 
 */
  
public class Assignment3a { 

	public static void main(String[] args) {
		
	}
	
	public static void divides() {
		if 
	}
	
	public static void abs(int x) {
		 if (x < 0) {
			x *= -1;
		 }
		 System.out.println("The absolute value is " + x);	
	}
	
	public static void max(int x, int y) {
		if (x == y ) {
			System.out.println("They are of equal value.");
		}
		else if (x > y) {
			System.out.println("The max is " + x);
		}
		else {
			System.out.println("The max is " + y);
		}
	}
	
	public static void maxMagnitude(int x, int y) {
		abs(x); abs(y);
		max(x, y);
	}
	
	public static void factor() {
		
	}
	
  
} 