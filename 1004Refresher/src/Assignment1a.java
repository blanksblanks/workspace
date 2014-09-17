/* Name: Nina B
 * UNI: nb2406
 * CS 1004, Summer 2014 
 * Programming Assignment 1a 
 */
  
/* Instructions 
 * Fill out the main below to do the following: 
 * Prompt the user to enter a number 
 * Get the number from the user 
 * Prompt the user to enter another number 
 * Get the number from the user 
 * Output, on one line each, without any other text: 
 *   1) the sum of the two numbers 
 *   2) the first minus the second 
 *   3) the first times the second 
 *   4) the first modulo the second 
 */

import java.util.Scanner; // Imports the scanner module

public class Assignment1a {
	
	public static void main(String[] args) {
		
		Scanner readin = new Scanner(System.in);
		
		System.out.print("Please enter a number: ");
		int first = readin.nextInt(); 
		
		System.out.print("Please enter another number: ");
		int second = readin.nextInt();
		
		System.out.println("The sum of the two numbers is " +
				(first + second) + ".");
		System.out.println("The first minus the second is " +
				(first - second) + ".");
		System.out.println("The first times the second is " +
				(first * second) + ".");
		System.out.println("The first modulo the second is " + 
				(first % second) + ".");
		
	}

}


