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
		System.out.println("10, 20\tDivisible? " + divides(10, 20));
		System.out.println("-20\tAbsolute: " + abs(-20));
		System.out.println("20, 10\tMax: " + max(20, 10));
		System.out.println("-20, 10\tMax magnitude: " + maxMagnitude(-20, 10));
		System.out.println("1\tFactor: " + factor(1));
		System.out.println("6\tFactor: " + factor(6));
	}
	
	public static Boolean divides(int x, int y) {
		boolean ans;
		if (y%x == 0) {
			ans = true;
		}
		else { ans = false; }
		return ans;
	}
	
	public static int abs(int x) {
		 if (x < 0) {
			x *= -1;
		 }
		 return x;	
	}
	
	public static int max(int x, int y) {
		int ans = 0;
		if (x == y ) {
			System.out.println("They are of equal value.");
		}
		else if (x > y) {
			ans = x;
		}
		else { ans = y; }
		return ans;
	}
	
	public static int maxMagnitude(int x, int y) {
		int ans = max(abs(x), abs(y));
		if (ans == abs(x)) {
			ans = x;
		}
		else { ans = y; }
		return ans;
	}
	
	public static int factor(int x) {
		int ans = 0;
		if (x == 1 | x == -1) {
			ans = 1;
		}
		else if (x == 0) {
			ans = 0;
		}
		else {
			for (int i = 2; i < x; i++) {
				if (x%i == 0) {
					ans = i;
					break;
				}
			}
		}
		return ans;
	}
	
  
} 