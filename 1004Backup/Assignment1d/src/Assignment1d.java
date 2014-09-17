/* Name: 
 * UNI: 
 * CS 1004, Summer 2014 
 * Programming Assignment 1d 
 */
  
/* Instructions 
 * Fill out the main below to do the following: 
 * For each of the basic types: 
 *   boolean 
 *   byte 
 *   short 
 *   int 
 *   long 
 *   float 
 *   double 
 *   char 
 * read in a value using a Scanner and store it in 
 * a variable of the given type. Then output it 
 * with System.out.println without any extra text. 
 * (read in the order above and write them out in 
 * the same order) 
 */

import java.util.Scanner;

public class Assignment1d {
	
	public static void main(String[] args) {
		
		Scanner readin = new Scanner(System.in);
		
		System.out.print("Please enter a boolean (True/False): ");
		boolean condition = readin.nextBoolean();
		System.out.print("Please enter a byte (8-bit ±128): ");
		// All these number ranges are actually from -n to (n-1)
		// Entering numbers outside of the range results in an error
		byte byteNum = readin.nextByte();
		System.out.print("Please enter a short (16-bit ±32,768): ");
		short shortNum = readin.nextShort();
		System.out.print("Please enter an int (32-bit ±2^31): ");
		int intNum = readin.nextInt();
		System.out.print("Please enter a long (64-bit ±2^63): ");
		long longNum = readin.nextLong();
		System.out.print("Please enter a float (precision 32-bit): ");
		float floatNum = readin.nextFloat();
		System.out.print("Please enter a double (precision 64-bit): ");
		double doubleNum = readin.nextDouble();
		System.out.print("Please enter a char: ");
		String character = readin.next();
		// no nextchar(), and next() String type could not be converted to char variable
		
		System.out.println("You entered: ");
		System.out.println(condition);
		System.out.println(byteNum);
		System.out.println(shortNum);
		System.out.println(longNum);
		System.out.println(floatNum);
		System.out.println(doubleNum);
		System.out.println(character);
		
	}

}
